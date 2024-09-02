package com.example.Mongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
@EnableElasticsearchRepositories(basePackages = "com.example.Mongo.Repository")
@SpringBootApplication
@ComponentScan(basePackages = "com.example.Mongo")
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

}
