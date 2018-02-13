package com.example.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MessageHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // The WebSocket has been closed
    	
    	sessionList.remove(session);
    }
 
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // The WebSocket has been opened
        // I might save this session object so that I can send messages to it outside of this method
 
        // Let's send the first message
        session.sendMessage(new TextMessage("You are now connected to the server. This is the first message."));
        
        sessionList.add(session);
    }
 
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        // A message has been received
        System.out.println("Message received: " + textMessage.getPayload());
    }
    
    @Scheduled(fixedDelayString = "10000")
    public void sendMessage() throws Exception {
    	
    	if(sessionList.size()>0) {
    		
    		System.out.println("Testing-1........");
    		
    		for(WebSocketSession session : sessionList) {
    			
    			System.out.println("Testing-2........");
    			session.sendMessage(new TextMessage("New update"));
    		}
    		
    	}
    }
}