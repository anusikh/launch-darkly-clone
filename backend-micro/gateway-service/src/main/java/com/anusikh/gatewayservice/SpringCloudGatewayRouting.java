package com.anusikh.gatewayservice;

import com.anusikh.gatewayservice.filter.MyGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {

    @Autowired
    private MyGatewayFilter filter;

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        // TODO: Make sure this filter be applied to other services not auth
        return builder.routes().route("auth", r -> r.path("/auth/**").uri("http://localhost:9009"))
                .route("teams", r -> r.path("/teams/**").filters(f -> f.filter(filter)).uri("http://localhost:9010"))
                .route("organisations",
                        r -> r.path("/organisations/**").filters(f -> f.filter(filter)).uri("http://localhost:9010"))
                .build();
    }
}
