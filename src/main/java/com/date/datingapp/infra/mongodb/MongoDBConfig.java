package com.date.datingapp.infra.mongodb;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Objects;

@Configuration
public class MongoDBConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    ConnectionString connectionString() {
        return new ConnectionString(mongoUri);
    }


    @Bean
    MongoClient mongoClient() {
        var mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString())
                .applicationName(applicationName)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    MongoTemplate mongoTemplate(
            MongoClient mongoClient,
            MongoMappingContext mongoMappingContext,
            ConnectionString connectionString) {
        var mongoDbFactory = new SimpleMongoClientDatabaseFactory(
                mongoClient,
                Objects.requireNonNull(connectionString.getDatabase()));

        var converter = new MappingMongoConverter(
                new DefaultDbRefResolver(mongoDbFactory),
                mongoMappingContext);

        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        converter.afterPropertiesSet();

        return new MongoTemplate(mongoDbFactory, converter);
    }
}
