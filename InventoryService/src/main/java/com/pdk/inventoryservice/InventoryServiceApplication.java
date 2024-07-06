package com.pdk.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.pdk.inventoryservice.Model.Inventory;
import com.pdk.inventoryservice.Repository.InventoryRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);

		System.out.println("-------------------------------");
		System.out.println("	Inventory Service");
		System.out.println("-------------------------------");
	}

	// @Bean
	// public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
	// return args -> {
	// Inventory inventory = new Inventory();
	// inventory.setSkuCode("iphone_13");
	// inventory.setQuantity(100);

	// Inventory inventory1 = new Inventory();
	// inventory1.setSkuCode("iphone_13_red");
	// inventory1.setQuantity(0);

	// inventoryRepository.save(inventory);
	// inventoryRepository.save(inventory1);
	// };
	// }

}
