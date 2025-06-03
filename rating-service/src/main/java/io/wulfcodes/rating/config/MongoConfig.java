package io.wulfcodes.rating.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("io.wulfcodes.rating.repository")
public class MongoConfig {}
