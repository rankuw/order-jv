package com.rankuw.inventoryservice.controller;

import com.rankuw.inventoryservice.dto.CheckItemsResponse;
import com.rankuw.inventoryservice.service.InventoryService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku}")
    public boolean checkItemExists(
            @PathVariable String sku
    ) {
        return inventoryService.checkItemAvailable(sku);
    }

    @GetMapping
    public List<CheckItemsResponse> checkItemsExist(
            @RequestParam List<String> skuCode
    ) {
        System.out.println(skuCode);
        return inventoryService.checkItemsAvailable(skuCode);
    }

}
