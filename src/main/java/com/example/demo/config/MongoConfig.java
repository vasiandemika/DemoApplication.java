package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTypeMapper mongoTypeMapper() {
        return new DefaultMongoTypeMapper(null);
    }
}
