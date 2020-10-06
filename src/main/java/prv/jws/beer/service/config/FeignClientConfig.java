package prv.jws.beer.service.config;

import feign.Logger;
import feign.Logger.Level;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jerzy Szymanski on 06.10.2020 at 11:27
 */
@Slf4j
@Configuration
public class FeignClientConfig {
    @Bean
    public BasicAuthRequestInterceptor basicAuthenticationInterceptor(@Value("${sfg.brewery.inventory.user}") String inventoryUser,
                                                                         @Value("${sfg.brewery.inventory.password}") String inventoryPassword) {
        log.info(" >>> user for inventory: {}, password {}", inventoryUser, inventoryPassword);
        return new BasicAuthRequestInterceptor(inventoryUser, inventoryPassword);
    }
//    @Bean
    Logger.Level feignLoggerLevel() {
        return Level.FULL;
    }
}
