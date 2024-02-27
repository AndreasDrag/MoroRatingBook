package com.moro.rating.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.moro.rating.book")
@EnableCaching
public class MoroRatingBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoroRatingBookApplication.class, args);
    }

}
