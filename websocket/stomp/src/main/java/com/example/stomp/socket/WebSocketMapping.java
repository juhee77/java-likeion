package com.example.stomp.socket;

import com.example.stomp.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketMapping {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    //pub 와 sub 패턴
    public void sendChat(ChatMessage chatMessage, @Headers Map<String, Object> headers) {
        log.info(chatMessage.toString());
        log.info(headers.toString()); //헤더도 이렇게 확인이 가능하다.
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        chatMessage.setTime(time);
        simpMessagingTemplate.convertAndSend(
                String.format("/topic/%s", chatMessage.getRoomId()),
                chatMessage
        );
    }
}
