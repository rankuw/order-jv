package com.rankuw.orderservice.service;

import com.rankuw.orderservice.dto.CheckItemsResponse;
import com.rankuw.orderservice.dto.OrderLineItemsDto;
import com.rankuw.orderservice.dto.OrderRequestDto;
import com.rankuw.orderservice.model.Order;
import com.rankuw.orderservice.model.OrderLineItems;
import com.rankuw.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void createOrder(OrderRequestDto orderRequestDto) {
        String orderNo = UUID.randomUUID().toString();
        List<OrderLineItems> orderLineItemsList = orderRequestDto.getOrderLineItemsDtoList().stream().map(this::orderLineItemsToOrderLinesDto).toList();

        List<String> skuCodes = orderLineItemsList.stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        CheckItemsResponse[] result = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(CheckItemsResponse[].class)
                .block();


        Boolean validOrder = result.length == skuCodes.size() && Arrays.stream(result).allMatch(CheckItemsResponse::isExits);
        System.out.println(validOrder);
        if(validOrder) {
            Order order = Order.builder()
                    .orderNumber(orderNo)
                    .orderLineItemsList(orderLineItemsList)
                    .build();
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Not all items available");
        }

    }


    private OrderLineItems orderLineItemsToOrderLinesDto(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .id(orderLineItemsDto.getId())
                .skuCode(orderLineItemsDto.getSku())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .build();
    }
}
