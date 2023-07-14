package com.lahee.gateway.filter;

import com.lahee.gateway.constant.constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

import static com.lahee.gateway.constant.constant.X_GATEWAY_REQUEST_ID;
import static com.lahee.gateway.constant.constant.X_GATEWAY_REQUEST_TIME;

@Component
@Slf4j
public class PreLoggingFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(
            // HttpServletRequest & Response 대신
            // exchange
            ServerWebExchange exchange,
            // FilterChain
            GatewayFilterChain chain
    ) {
        //사용자가 보낸 HTTP 요청을 받았다.
        ServerHttpRequest httpRequest = exchange.getRequest();
        // PreLoggingFilter에서 요청을 식별할 수 있는 HTTP Header를 작성
        // 나중에 PostLoggingFilter에서 해당 Header을 바탕으로
        // 실행에 걸린 시간 측정

        //사용자가 보낸 요청을 조작(변형) 하겠다.
        String requestId = UUID.randomUUID().toString();
        httpRequest.mutate()
                //헤더를 변형한다.
                .headers(httpHeaders -> {
                    httpHeaders.add(
                            X_GATEWAY_REQUEST_ID,
                            requestId
                    );
                    httpHeaders.add(
                            X_GATEWAY_REQUEST_TIME,
                            String.valueOf(Instant.now().toEpochMilli())
                    );
                });
        log.info("start transaction: {}", requestId);
        return chain.filter(exchange);
    }
}
