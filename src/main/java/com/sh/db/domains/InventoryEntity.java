package com.sh.db.domains;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class InventoryEntity implements Serializable{
	@Id
	private String productId;
	private String productName;
	private int quantity;
	private int maxQuantity;
}
