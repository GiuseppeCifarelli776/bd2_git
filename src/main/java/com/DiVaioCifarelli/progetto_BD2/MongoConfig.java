package com.DiVaioCifarelli.progetto_BD2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@SuppressWarnings("deprecation")
@Configuration
public class MongoConfig{
  
    @Bean
    public MongoDbFactory mongoDbFactory() {

        MongoClient mongoClient = new MongoClient("127.0.0.1:27017");

        return new SimpleMongoDbFactory(mongoClient, "ProgettoDB2" );
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
