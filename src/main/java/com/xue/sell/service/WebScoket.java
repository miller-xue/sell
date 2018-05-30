package com.xue.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * webSocket
 * Created by miller on 2018/5/23
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebScoket {

    private Session session;

    private static CopyOnWriteArraySet<WebScoket> webScoketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webScoketSet.add(this);
        log.info("【webSocket消息】 有新的连接, 总数:{}",webScoketSet.size());
    }

    @OnClose
    public void onClose(){
        webScoketSet.remove(this);
        log.info("【webSocket消息】 连接断开, 总数:{}", webScoketSet.size());
    }


    @OnMessage
    public void onMessage(String message){
        log.info("【webSocket消息】 收到客户端发来的消息:{}",message);
    }

    public void sendMessage(String message){
        for (WebScoket webScoket : webScoketSet){
            log.info("【webSocket消息】 广播消息, message={}",message);
            try {
                webScoket.session.getBasicRemote().sendText(message);
            }catch (Exception e){//TODO 发送消息的异常直接自己捕获处理
                e.printStackTrace();
            }
        }
    }
}
