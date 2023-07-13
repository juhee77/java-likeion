package com.example.chat.config;

import com.example.chat.SimpleChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final SimpleChatHandler simpleChatHandler;

    @Override
    //WebSocketHandler 객체를 등록하기 위한 메소드
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //받는 URL 매핑?
        //CORS 문제 처리를 위한
        registry.addHandler(simpleChatHandler, "ws/chat").setAllowedOriginPatterns("*");
    }
}
