package com.example.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint("/webSocket")
@Component
@Slf4j
public class WebSocket {

    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    @OnClose
    public void close() {
        webSockets.remove(this);
        log.info("websocket close size={}", webSockets.size());
    }


    @OnOpen
    public void open(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("websocket open size={}", webSockets.size());
    }


    @OnMessage
    public void onMessage(String message) {

        log.info("websocket message={}", message);
    }


    public void sendMessage(String msg) {
        log.info("websocket sendMessage msg={}", msg);
        for (WebSocket webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
