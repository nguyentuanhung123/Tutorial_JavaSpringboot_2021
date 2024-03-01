package com.example.Beststore3.database;

import com.example.Beststore3.models.Product;
import com.example.Beststore3.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Khơi tạo database ngay khi ứng dụng đuọc chạy
public class Database {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Macbook pro 16 inch", 2020, 2400.0, "");
                Product productB = new Product("Ipad air green", 2021, 599.0, "");
                Product productC = new Product("Ipad air blue", 2022, 756.0, "");
                Product productD = new Product("Ipad air white", 2023, 821.5, "");
                //System.out.println("insert data: " + productRepository.save(productA));
                //System.out.println("insert data: " + productRepository.save(productB));
                //System.out.println("insert data: " + productRepository.save(productC));
                logger.info("insert data: " + productRepository.save(productA));
                logger.info("insert data: " + productRepository.save(productB));
                logger.info("insert data: " + productRepository.save(productC));
                logger.info("insert data: " + productRepository.save(productD));
            }
        };
    }
}
