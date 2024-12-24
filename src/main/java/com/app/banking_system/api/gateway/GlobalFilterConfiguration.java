package com.app.banking_system.api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfiguration {

    final Logger logger = LoggerFactory.getLogger(GlobalFilterConfiguration.class);

    @Bean
    @Order(1)
    public GlobalFilter secondFilter(){
            return (exchange,chain) -> {
                logger.info("My Second pre-filter is executed");

                return chain.filter(exchange).then(Mono.fromRunnable(()->{
                    logger.info("My Second Post filter executed...");
                }));
            };
    }

    @Bean
    @Order(2)
    public GlobalFilter thirdFilter(){
        return (exchange,chain)->{
            logger.info("My third pre-filter is executed...");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("My Third post-filter is executed...");
            }));
        };
    }

    @Bean
    @Order(2)
    public GlobalFilter fourthFilter(){
        return (exchange,chain)->{
            logger.info("Fourth global Pre -filter executed...");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("Fourth post-filter executed...");
            }));
        };
    }



}
