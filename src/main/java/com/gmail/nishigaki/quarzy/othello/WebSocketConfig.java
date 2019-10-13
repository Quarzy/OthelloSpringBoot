package com.gmail.nishigaki.quarzy.othello;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author nishigaki
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(final MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(final StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket")
				.setAllowedOrigins("*")
				.addInterceptors(new CustomHandshakeInterceptor())
				.withSockJS()
//				.setInterceptors(new CustomHandshakeInterceptor())
				.setWebSocketEnabled(true);
	}

//	@Autowired
//	private WebSocketHandler webSocketHandler;
//
//	@Bean
//	public HandlerMapping webSocketHandlerMapping() {
//		final Map<String, WebSocketHandler> map = new HashMap<>();
//		map.put("/app", webSocketHandler);
//
//		final SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//		mapping.setOrder(1);
//		mapping.setUrlMap(map);
//		return mapping;
//	}
}
