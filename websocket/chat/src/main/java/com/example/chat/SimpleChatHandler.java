package com.example.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SimpleChatHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new ArrayList<>();

    public void broadcast(String message) throws IOException {
        for (WebSocketSession connected: sessions) {
            connected.sendMessage(new TextMessage(message));
        }
    }

    @Override
    //웹 소켓 세션이 성립되었다. 연결된 사용자를 구분할 수 있게 된다.
    //웹 소켓 최초 연결시 실행
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session); //현재 사용자 저장
        log.info("connected with session id :{}, total Session : {}", session.getId(), sessions.size());
    }

    @Override
    //소켓의 메세지를 받으면 실행될것 이다. (페이로드를 문자열로 자동으로 변환해준다)
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //데이터를 받는다.
        String payload = message.getPayload();
        log.info("receiver : {}", payload);
        //해당 정보를 다시 전달한다.
        for (WebSocketSession connected: sessions) {
            connected.sendMessage(message);
        }
    }

    @Override
    //WebSocket 연결이 종료 되었을때
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("connection with closed ");
        sessions.remove(session); //세션을 삭제한다.
    }
}
