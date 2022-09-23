package com.anusikh.gatewayservice.filter;


import com.anusikh.gatewayservice.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyGatewayFilter implements GatewayFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();
        final String token = request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);
        System.out.println(token);
        Claims claims = jwtUtil.getClaims(token);

        exchange.getRequest().mutate().header("username", claims.getSubject()).build();

        return chain.filter(exchange);
    }
}
