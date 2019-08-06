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
	//main business logic method
	public List<String> createOrder(int userId,String[] productIdList,String[] productsCountList) {
		boolean validAccount = checkForValidAccount(userId);
		List<String> errorList = new ArrayList<>();
		//if invalid account return error
		if(validAccount) {
			for(int i=0;i<productIdList.length;i++) {
				InventoryEntity inventoryEntity = getQuantityOfProduct(productIdList[i]);
				if(inventoryEntity != null) {
					//not making partial orders
					if(Integer.parseInt(productsCountList[i]) <= inventoryEntity.getMaxQuantity()) {
						if(Integer.parseInt(productsCountList[i]) <= inventoryEntity.getQuantity()) {
							//1. reducing inventory product quantity by ordered quantity to avoid race condition
							//2. However if two requests simultaneously access the products inventory then transactions
							//can be used to roll back payment if product quantity is <=0 and display out of stock error
							//payment code shoukd also execute in this flow to make it trasactional with inventory quantity
							//3. We cannot take a lock on the following section as it will be used by every product
							//4. There is already a lock in mongodb on document level simultaneous updates of product 
							//quantity can be handled by that.
							mongoChannel.reduceProductQuantity(inventoryEntity,productIdList[i],Integer.parseInt(productsCountList[i]));
							//creating separate order ids for every product for partial order cancellation later
							mongoChannel.createOrderEntry(userId,productIdList[i],Integer.parseInt(productsCountList[i]));
						}
						else {
							//partial orders for a product cannot be made without informing the user
							errorList.add("Product '" + inventoryEntity.getProductName() + "' is out of stock");
						}
					}
					else
						errorList.add("Maximum quantity that can be ordered for the product '" + inventoryEntity.getProductName() + "' is "+inventoryEntity.getMaxQuantity());
				}
				else
					errorList.add("Product '" + productIdList[i] + "' is invalid");
			}
		}
		else
			errorList.add("Invalid account to order");
		return errorList;
	}
	
	public InventoryEntity getQuantityOfProduct(String productId) {
		return mongoChannel.getInventoryProdQuant(productId);
	}
	
	//checks if the account exists and is valid
	public boolean checkForValidAccount(Integer userId) {
		AccountsEntity ac = mongoChannel.getAccountInfo(userId);
		if(ac != null && ac.getUserEmail() != null) {
			return true;
		}
		return false;
	}
}
