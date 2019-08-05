package com.sh.configs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("mongo")
@EnableMongoRepositories(basePackages = "com.sh.db.repos.mongo")
public class MongoConfig {
	private List<String> hosts;

	private List<Integer> ports;
	
	private String db;

	private int connectionTimeout;
	
	@Bean
	public Mongo mongo() {
		List<ServerAddress> serverAddresses = new ArrayList<>();
		List<MongoCredential> mongoCredentials = new ArrayList<>();
		for (int idx = 0; idx < hosts.size(); idx++) {
			serverAddresses.add(new ServerAddress(hosts.get(idx), ports.get(idx)));
			//mongoCredentials.add(MongoCredential.createCredential(usernames.get(idx), db, passwords.get(idx).toCharArray()));
		}

		Builder configBuilder = MongoClientOptions.builder();
		configBuilder.connectTimeout(connectionTimeout);

		return new MongoClient(serverAddresses, mongoCredentials, configBuilder.build());
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongo(), db);
	}
}
