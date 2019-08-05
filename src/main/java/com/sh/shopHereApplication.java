package com.sh;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import com.sh.db.domains.AccountsEntity;
import com.sh.db.domains.InventoryEntity;
import com.sh.db.domains.OrdersEntity;
import com.sh.db.repos.mongo.AccountsRepository;
import com.sh.db.repos.mongo.InventoryRepository;
import com.sh.db.repos.mongo.OrdersRepository;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
public class shopHereApplication implements CommandLineRunner {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	public static void main(String args[]) {
		SpringApplication.run(shopHereApplication.class,args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		AccountsEntity accountsEntity = new AccountsEntity();
		accountsEntity.setUserEmail("safsa@sfn.kjn");
		accountsEntity.setUserId(1);
		accountsEntity.setUserName("awdfaw");
		accountsRepository.save(accountsEntity);
		
		InventoryEntity inventoryEntity = new InventoryEntity();
		inventoryEntity.setProductId("jj16");
		inventoryEntity.setProductName("battery");
		inventoryEntity.setQuantity(20);
		inventoryRepository.save(inventoryEntity);
		
		OrdersEntity ordersEntity = new OrdersEntity();
		ordersEntity.setOrderId("1_8989898");
		ordersEntity.setProductId("jj16");
		ordersEntity.setUserId(1);
		ordersRepository.save(ordersEntity);
	}
}
