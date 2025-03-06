//package com.bms.gateway_service.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Component
//public class JWTFilter implements GatewayFilter {
//
//    private final WebClient webClient;
//
//    public JWTFilter(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("http://security-service").build();
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String path = exchange.getRequest().getURI().getPath();
//
//        // Allow public routes without token validation
//        if (path.equals("/user/login") || path.equals("/user/register")) {
//            return chain.filter(exchange);
//        }
//
//        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//
//            return webClient.post()
//                    .uri("/auth/token/validate")
//                    .body(BodyInserters.fromValue(token)) // Send token securely in body
//                    .retrieve()
//                    .bodyToMono(Boolean.class)
//                    .flatMap(isValid -> {
//                        if (Boolean.TRUE.equals(isValid)) {
//                            return chain.filter(exchange);
//                        } else {
//                            log.warn("Invalid token for request to: {}", path);
//                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                            return exchange.getResponse().setComplete();
//                        }
//                    })
//                    .onErrorResume(e -> {
//                        log.error("Error validating token: {}", e.getMessage());
//                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                        return exchange.getResponse().setComplete();
//                    });
//        }
//
//        log.warn("Missing Authorization header for request to: {}", path);
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        return exchange.getResponse().setComplete();
//    }
//}
