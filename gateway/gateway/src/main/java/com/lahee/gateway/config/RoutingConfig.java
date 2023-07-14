package com.lahee.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfig {

    @Bean
    // 라우팅
    // 어떤식으로 요청을 사용자에게서 다른 서버로 잘 보낼 것인지를
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // http://localhost:8080/articles/**
                // (/articles, /articles/{id}, /articles/{id}/comments}
                // -> http://localhost:8082/articles/**
                .route("articles", predicate -> predicate
                        //사용자가 보낸 PATH모습
                        .path("/articles/**")
                        //사용자가 보낼 요청을 보내는 HOST
                        .uri("http://localhost:8082")
                )
                //지금 목표는 8081번에
                .route("auth", predicate -> predicate
                                .path("/auth/**")
                                .filters(filter -> filter
//                                .rewritePath(
//                                        //정규 표현식 문법을 활용하여 경로의 일부분 추출
//                                        //해당 부분에 해당 되는 내용이 8081 뒤에 붙여 전달해준다.
//                                        "/auth/(?<path>.*)",
//                                        "/${path}"
//                                )) //게이트 웨이 전용 필터 ( 서블렛 필터 아님)
                                                .rewritePath(
                                                        "/auth/(?<path>.*)",
                                                        "/${path}"
                                                )
                                )
                                .uri("http://localhost:8081")
                )
                .build();
    }
}