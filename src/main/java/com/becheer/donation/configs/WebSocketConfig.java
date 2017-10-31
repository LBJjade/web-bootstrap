package com.becheer.donation.configs;


import com.becheer.donation.interceptor.WebSocketHandshakeInterceptor;
import com.becheer.donation.utils.ChatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {



    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/endpointWisely").setAllowedOrigins("*").withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/topic");
//        registry.enableSimpleBroker("/topic","/user");
//        registry.setApplicationDestinationPrefixes("/app/");
//        registry.setUserDestinationPrefix("/user/");



    }



}
