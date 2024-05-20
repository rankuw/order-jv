package com.rankuw.inventoryservice;

import com.rankuw.inventoryservice.entity.Inventory;
import com.rankuw.inventoryservice.repository.InventoryRepository;
import com.rankuw.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {

	@Autowired
	private InventoryRepository inventoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Inventory inventory = Inventory.builder()
				.id(1)
				.sku("124d")
				.quantity(10)
				.build();

		Inventory inventory1 = Inventory.builder()
				.id(2)
				.sku("164d")
				.quantity(10)
				.build();
		inventoryRepository.save(inventory);
		inventoryRepository.save(inventory1);
	}
}
