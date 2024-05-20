package com.rankuw.orderservice.controller;

import com.rankuw.orderservice.dto.OrderRequestDto;
import com.rankuw.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(
            @RequestBody OrderRequestDto orderRequestDto
    ) {
        System.out.println(orderRequestDto);

        orderService.createOrder(orderRequestDto);

    }
}
