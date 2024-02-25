package com.moro.rating.book.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BookClientRestConfig {

    @Bean(name = "bookClientRestTemplate")
    public RestTemplate bookClientRestTemplate() {
        return new RestTemplate();
    }

}
