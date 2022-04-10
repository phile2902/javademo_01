package com.phile.demo2.database;

import com.phile.demo2.model.Product;
import com.phile.demo2.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    public CommandLineRunner initDatabase(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                ArrayList<Product> list = new ArrayList<Product>();
                list.add(new Product("A", 2020, 2400.5, ""));
                list.add(new Product("B", 2021, 2400.8, ""));
                logger.info("Insert data:" + productRepository.saveAll(list));
            }
        };
    }
}
