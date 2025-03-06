package com.bms.gateway_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class GatewayConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply CORS to all paths
                .allowedOrigins("*")  // Allow all origins
                .allowedMethods("*")  // Allow all methods (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*");
    }
}
