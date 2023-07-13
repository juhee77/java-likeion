package com.example.stomp.socket;

import com.example.stomp.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketMapping {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    //pub 와 sub 패턴
    public void sendChat(ChatMessage chatMessage) {
        log.info(chatMessage.toString());
        String time = new SimpleDateFormat("HH:mm").toString();
        chatMessage.setTime(time);
        simpMessagingTemplate.convertAndSend(
                String.format("/topic/%s", chatMessage.getRoomId()),
                chatMessage
        );
    }
}
