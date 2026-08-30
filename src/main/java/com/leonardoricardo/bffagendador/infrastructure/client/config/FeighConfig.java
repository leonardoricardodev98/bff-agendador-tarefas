package com.leonardoricardo.bffagendador.infrastructure.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeighConfig {

    @Bean
    public FeignError feighError(){
        return new FeignError();
    }
}
