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
        // 메세지 브로커를 활성화, 구독자 주소의 접두어로 해당 주소를 구독한 클라이언트에게 메시지를 전달할 수 있도록한다.
        // topic으로 시작하는 주소로 구독한 클라이언트에게 서버에서 메시지를 전송할 수 있다.
        registry.enableSimpleBroker("/topic");
        // 애플리케이션의 목적지 접두사 메시지 발행 주소로 나타내어
        // app,topic으로 시작하는 주소를 클라이언트의 발행 주소로 설정하여 클라이언트가 서버로 메세지를 발행할 수 있도록한다.
        registry.setApplicationDestinationPrefixes("/topic","/app");
    }
}
