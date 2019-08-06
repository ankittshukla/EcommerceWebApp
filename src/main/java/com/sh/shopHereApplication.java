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
		//setting default dummy db values
		AccountsEntity accountsEntity = new AccountsEntity();
		accountsEntity.setUserEmail("safsa@sfn.kjn");
		accountsEntity.setUserId(101);
		accountsEntity.setUserName("awdfaw");
		accountsRepository.save(accountsEntity);
		
		AccountsEntity accountsEntity2 = new AccountsEntity();
		accountsEntity2.setUserEmail("sfsee@swewfn.keejn");
		accountsEntity2.setUserId(101);
		accountsEntity2.setUserName("aweeee");
		accountsRepository.save(accountsEntity2);
		
		InventoryEntity inventoryEntity = new InventoryEntity();
		inventoryEntity.setProductId("jj16");
		inventoryEntity.setProductName("battery");
		inventoryEntity.setQuantity(20);
		inventoryEntity.setMaxQuantity(6);
		inventoryRepository.save(inventoryEntity);
		
		InventoryEntity inventoryEntity2 = new InventoryEntity();
		inventoryEntity2.setProductId("jj17");
		inventoryEntity2.setProductName("guitar");
		inventoryEntity2.setQuantity(13);
		inventoryEntity2.setMaxQuantity(3);
		inventoryRepository.save(inventoryEntity2);
		
		OrdersEntity ordersEntity = new OrdersEntity();
		ordersEntity.setOrderId("1_8989898");
		ordersEntity.setProductId("jj16");
		ordersEntity.setUserId(1);
		ordersEntity.setQuantity(3);
		ordersRepository.save(ordersEntity);
	}
}
