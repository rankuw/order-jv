package com.rankuw.inventoryservice.service;

import com.rankuw.inventoryservice.dto.CheckItemsResponse;
import com.rankuw.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean checkItemAvailable(String sku) {
        return inventoryRepository.findItemBySku(sku).isPresent();
    }

    public List<CheckItemsResponse> checkItemsAvailable(List<String> skuCodes) {
        System.out.println(inventoryRepository.findBySkuIn(skuCodes));
        return inventoryRepository.findBySkuIn(skuCodes)
                .stream()
                .map(item ->
                    CheckItemsResponse.builder()
                            .sku(item.getSku())
                            .exits(item.getQuantity() > 0)
                            .build()
                ).toList();


    }
}
