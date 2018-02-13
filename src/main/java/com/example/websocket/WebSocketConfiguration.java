package com.example.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
 
    @Bean
    public WebSocketHandler myMessageHandler() {
        return new MessageHandler();
    }
 
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myMessageHandler(), "/my-websocket-endpoint");
    }
 
}