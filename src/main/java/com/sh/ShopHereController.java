package com.sh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan({ "com.sh.*" })
@EntityScan("com.sh.*")
@EnableMongoRepositories(basePackages = "com.sh.*")
public class ShopHereController {
	@Autowired
	OrderCreator orderCreator;
	
	@RequestMapping(value="/createOrder",method = {RequestMethod.POST})
	public ResponseFormat createOrder(
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "productIds", required = true) String productIds,
			@RequestParam(value = "productCounts", required = true) String productCounts) {
		
		String[] productIdList = productIds.split(",");
		String[] productCountList = productCounts.split(",");
		List<String> orderCreatedErrors = orderCreator.createOrder(userId,productIdList,productCountList);
		ResponseFormat response = new ResponseFormat();
		//generating response
		if(orderCreatedErrors.isEmpty()) {
			response.setStatusCode(200);
			response.getResponseMessage("order placed");
		}
		else {
			response.setStatusCode(400);
			response.getResponseMessage(String.join("\n",orderCreatedErrors));
		}
		return response;
	}
}
