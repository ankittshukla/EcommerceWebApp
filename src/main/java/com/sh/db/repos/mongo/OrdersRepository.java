package com.sh.db.repos.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sh.db.domains.OrdersEntity;

@Repository
public interface OrdersRepository extends MongoRepository<OrdersEntity,Integer>{
	public OrdersEntity findOrderInfoByOrderId(int orderId);

}
