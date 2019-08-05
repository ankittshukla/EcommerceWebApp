package com.sh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.channels.MongoChannel;
import com.sh.db.domains.AccountsEntity;
import com.sh.db.domains.InventoryEntity;

@Service
public class OrderCreator {
	
	@Autowired
	private MongoChannel mongoChannel;
	
	public List<String> createOrder(int userId,String[] productIdList,String[] productsCountList) {
		boolean validAccount = checkForValidAccount(userId);
		List<String> errorList = new ArrayList<>();
		//if invalid account return error
		if(validAccount) {
			for(int i=0;i<productIdList.length;i++) {
				InventoryEntity inventoryEntity = getQuantityOfProduct(productIdList[i]);
				if(Integer.parseInt(productsCountList[i]) < inventoryEntity.getQuantity()) {
					//reducing inventory product quantity by ordered quantity
					mongoChannel.reduceProductQuantity(inventoryEntity,productIdList[i],Integer.parseInt(productsCountList[i]));
					//creating separate order ids for every product for partial order cancellation later
					mongoChannel.createOrderEntry(userId,productIdList[i],Integer.parseInt(productsCountList[i]));
				}
				else {
					errorList.add("Product" + productIdList[i] + "is out of stock");
				}
			}
		}
		else
			errorList.add("Invalid account to order");
		return errorList;
	}
	
	public InventoryEntity getQuantityOfProduct(String productId) {
		return mongoChannel.getInventoryProdQuant(productId);
	}
	public boolean checkForValidAccount(Integer userId) {
		AccountsEntity ac = mongoChannel.getAccountInfo(userId);
		if(ac.getUserEmail() != null) {
			return true;
		}
		return false;
	}
}
