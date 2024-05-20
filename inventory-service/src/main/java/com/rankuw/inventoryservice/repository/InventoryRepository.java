package com.rankuw.inventoryservice.repository;

import com.rankuw.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findItemBySku(String sku);
    List <Inventory> findBySkuIn(List<String> sku);
}
