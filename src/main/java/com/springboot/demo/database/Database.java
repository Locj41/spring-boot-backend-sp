package com.springboot.demo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.demo.models.Product;
import com.springboot.demo.repository.ProductRepository;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {

        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("iphone", 2020, 3000.0, "");
                Product productB = new Product("ipad", 2019, 200.0, "");
                logger.info("inserted data: " + productRepository.save(productA));
                logger.info("inserted data: " + productRepository.save(productB));
            }
        };
    }
}
