package com.sh.db.domains;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class AccountsEntity implements Serializable{
	@Id
	int userId;
	String userEmail;
	String userName;
}
