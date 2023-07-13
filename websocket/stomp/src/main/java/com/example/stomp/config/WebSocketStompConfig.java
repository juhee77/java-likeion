package com.example.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    //stomp endpoint 설정 메소드
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chatting");
    }

    @Override
    //웹 소켓 말고 메세지 브로커(메세지를 주고받는 미들웨어)
    //메세지 브로커를 활용하는 방법 설정
    //어디로 메세지를 연결할건지
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
