package com.gmail.nishigaki.quarzy.othello;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @author nishigaki
 */
public class CustomHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(final ServerHttpRequest serverHttpRequest,
			final ServerHttpResponse serverHttpResponse,
			final WebSocketHandler webSocketHandler, final Map<String, Object> map) throws Exception {
		System.out.println("BEFORE HANDSHAKE");
		if (serverHttpRequest instanceof ServletServerHttpRequest) {
			final ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
			final HttpSession session = servletRequest.getServletRequest().getSession();
			map.put("sessionId", session.getId());
		}
		return true;
	}

	@Override
	public void afterHandshake(final ServerHttpRequest serverHttpRequest,
			final ServerHttpResponse serverHttpResponse,
			final WebSocketHandler webSocketHandler, final Exception e) {
		System.out.println("AFTER HANDSHAKE");
	}
}
