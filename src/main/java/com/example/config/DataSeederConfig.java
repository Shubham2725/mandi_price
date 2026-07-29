package com.example.config;

import com.example.entity.Product;
import com.example.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

/**
 * Seeds the product catalog with default fruits on application startup.
 * Guarded by a count check so this is safe to run on every restart
 * without violating the unique constraint on {@code Product.name}.
 */
@Configuration
@RequiredArgsConstructor
public class DataSeederConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSeederConfig.class);

    private final ProductRepository productRepository;

    @org.springframework.context.annotation.Bean
    public CommandLineRunner seedProducts() {
        return args -> {
            if (productRepository.count() > 0) {
                log.info("Products already seeded, skipping.");
                return;
            }

            List<Product> defaults = List.of(
                    new Product("Apple", "Crisp and juicy red apples", new BigDecimal("120.00"), 50),
                    new Product("Orange", "Sweet and tangy oranges", new BigDecimal("80.00"), 60),
                    new Product("Pomegranate", "Ruby-red antioxidant-rich pomegranates", new BigDecimal("150.00"), 40),
                    new Product("Sapota", "Sweet, malty-flavored sapota (chikoo)", new BigDecimal("90.00"), 35)
            );

            productRepository.saveAll(defaults);
            log.info("Seeded {} default products.", defaults.size());
        };
    }
}
