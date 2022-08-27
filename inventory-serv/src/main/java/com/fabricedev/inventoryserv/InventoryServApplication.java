package com.fabricedev.inventoryserv;

import com.fabricedev.inventoryserv.model.Inventory;
import com.fabricedev.inventoryserv.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return  args -> {
			Inventory inventory = new Inventory();

			inventory.setSkuCode("Iphone13");
			inventory.setQuantity(12);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Iphone13_red");
			inventory1.setQuantity(0);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("SamsungS22");
			inventory2.setQuantity(23);

			//----------Save inventory-----------
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}

}
