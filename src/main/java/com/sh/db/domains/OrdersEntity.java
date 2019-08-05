package com.sh.db.domains;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class OrdersEntity implements Serializable{
	@Id
	private String orderId;
	private String productId;
	private int quantity;
	private int userId;
}
