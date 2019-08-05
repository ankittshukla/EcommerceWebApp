package com.sh.db.repos.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sh.db.domains.InventoryEntity;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryEntity,String>{
	public InventoryEntity findDocumentByProductId(String productId);

}
