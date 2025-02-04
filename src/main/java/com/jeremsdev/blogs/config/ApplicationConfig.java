package com.jeremsdev.blogs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@RequiredArgsConstructor
@EnableMongoRepositories(basePackages = "com.jeremsdev.blogs.repository.mongo")
@EnableElasticsearchRepositories(basePackages = "com.jeremsdev.blogs.repository.elasticsearch")
public class ApplicationConfig {

}
