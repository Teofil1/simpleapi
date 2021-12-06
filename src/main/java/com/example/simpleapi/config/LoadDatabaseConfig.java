package com.example.simpleapi.config;

import com.example.simpleapi.model.entity.Item;
import com.example.simpleapi.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class LoadDatabaseConfig {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabaseConfig.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepository repository) {

        return args -> {
            Item item1 = repository.save(new Item(UUID.randomUUID().toString(), "item1"));
            log.info("Preloading item with id: " + item1.getId());
            Item item2 = repository.save(new Item(UUID.randomUUID().toString(), "item2"));
            log.info("Preloading item with id: " + item2.getId());
        };
    }
}
