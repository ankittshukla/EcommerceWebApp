package com.sh.channels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.db.domains.AccountsEntity;
import com.sh.db.domains.InventoryEntity;
import com.sh.db.domains.OrdersEntity;
import com.sh.db.repos.mongo.AccountsRepository;
import com.sh.db.repos.mongo.InventoryRepository;
import com.sh.db.repos.mongo.OrdersRepository;

@Service
public class MongoChannel {
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	public AccountsEntity getAccountInfo(Integer userId){
		return accountsRepository.findAccountInfoByUserId(userId);
	}
	
	public InventoryEntity getInventoryProdQuant(String prodId){
		InventoryEntity inventoryEntity = inventoryRepository.findDocumentByProductId(prodId);
		return inventoryEntity;
	}
	
	public void createOrderEntry(int userId,String prodId,int quantity) {
		OrdersEntity ordersEntity = new OrdersEntity();
		ordersEntity.setOrderId(String.valueOf(userId)+"_"+System.currentTimeMillis() / 1000L);
		ordersEntity.setProductId(prodId);
		ordersEntity.setQuantity(quantity);
		ordersEntity.setUserId(userId);
		ordersRepository.save(ordersEntity);
	}
	
	public void reduceProductQuantity(InventoryEntity inventoryEntity,String productId,int quantityToReduce) {
		inventoryEntity.setQuantity(inventoryEntity.getQuantity()-quantityToReduce);
		inventoryRepository.save(inventoryEntity);
	}
}
