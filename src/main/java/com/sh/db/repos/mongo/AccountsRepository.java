package com.sh.db.repos.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sh.db.domains.AccountsEntity;

@Repository
public interface AccountsRepository extends MongoRepository<AccountsEntity,Integer>{
	public AccountsEntity findAccountInfoByUserId(int userId);

}
