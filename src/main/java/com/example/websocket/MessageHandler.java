package com.example.websocket;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.model.MessageData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	private Integer count = 10;
	 
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
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	MessageData data = new MessageData();
    	
    	data.setMessageId(1);
    	data.setMessage("Message from server");
    	data.setMessage_desc("java object");
    	data.setDate(new Timestamp(new Date().getTime()));
    	
    	String msg_data = mapper.writeValueAsString(data);
    	
        session.sendMessage(new TextMessage("You are now connected to the server. Data -->" + msg_data));
        
        sessionList.add(session);
    }
 
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        // A message has been received
        System.out.println("Message received: " + textMessage.getPayload());
    }
    
    @Scheduled(fixedDelayString = "1000")
    public void sendMessage() throws Exception {
    	
    	if(sessionList.size()>0) {
    		
    		System.out.println("Testing-1........");
    		
    		for(WebSocketSession session : sessionList) {
    			
    			System.out.println("Testing-2........");
    			session.sendMessage(new TextMessage(""+count));
    			//session.sendMessage(new TextMessage("New update -- " + count ));
    			count=count+10;
    		}
    		
    	}
    }
}