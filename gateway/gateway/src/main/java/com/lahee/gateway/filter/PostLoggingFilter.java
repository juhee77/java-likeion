package com.lahee.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static com.lahee.gateway.constant.constant.X_GATEWAY_REQUEST_ID;
import static com.lahee.gateway.constant.constant.X_GATEWAY_REQUEST_TIME;

@Slf4j
@Component
public class PostLoggingFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //사용자에게 응답이 돌아가고 난 뒤에 실행하는 필터
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // 사용자가 보낸 HTTP 요청을 받았다.
                    ServerHttpRequest httpRequest = exchange.getRequest();
                    // PreLoggingFilter에서 만든 Header의 값을 받아 왔다.
                    String requestId = httpRequest.getHeaders().getFirst(X_GATEWAY_REQUEST_ID);
                    String requestTimeString = httpRequest.getHeaders().getFirst(X_GATEWAY_REQUEST_TIME);
                    // 현재 시각
                    long timeEnd = Instant.now().toEpochMilli(); // PreLoggingFilter에서 기록한 시각
                    long timeStart = Long.parseLong(requestTimeString); //TODO 위험하다!
                    //실생활에서는 이렇게 하는 경우가 많지 않다
                    //실제 예시는 깃에 있음

                    // 기록한다.
                    log.info("Execution Time id: {}, timediff(ms): {}",
                            requestId, timeEnd - timeStart);

                }));
    }
}
