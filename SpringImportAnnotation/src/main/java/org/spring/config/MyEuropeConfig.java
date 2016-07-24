package org.spring.config;

import org.spring.bean.HelloEurope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Constantin Alin
 */
@Configuration
public class MyEuropeConfig {

    public MyEuropeConfig() {
    }

    @Bean
    public HelloEurope helloEurope() {
        return new HelloEurope();
    }

}
