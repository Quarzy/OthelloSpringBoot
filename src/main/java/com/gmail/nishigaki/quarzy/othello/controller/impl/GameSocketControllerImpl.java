package com.gmail.nishigaki.quarzy.othello.controller.impl;

import java.io.IOException;
import java.security.Principal;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.nishigaki.quarzy.othello.controller.GameSocketController;
import com.gmail.nishigaki.quarzy.othello.model.GameReadResult;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import com.gmail.nishigaki.quarzy.othello.service.GameService;
import lombok.Data;

/**
 * @author nishigaki
 */
@RestController
public class GameSocketControllerImpl implements GameSocketController {

//	@Autowired
//	private SimpMessageSendingOperations simpMessageSendingOperations;

	@Autowired
	private GameService gameService;

//	@Override
//	public void game(final Principal principal, final String payload) {
////		final Object name = session.getAttribute("name");
//		simpMessageSendingOperations.convertAndSendToUser(principal.getName(), "/queue/assigned", "hoge");
//	}
//
//	@Override
//	public String game2(final Principal principal, final String payload) {
//		return payload;
//	}

	@Override
	public final String game(final String payload) {
		final GameReadResult result = read(payload);
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}

	private GameReadResult read(final String payload) {
		final ObjectMapper mapper = new ObjectMapper();
		final Payload payloadObject;
		try {
			payloadObject = mapper.readValue(payload, Payload.class);

		} catch (IOException e) {
			throw new RuntimeException();
		}
		final PieceValue pieceValue;
		switch (PieceValue.valueOf(payloadObject.getPiece())) {
			case WHITE:
				pieceValue = PieceValue.BLACK;
				break;
			case BLACK:
				pieceValue = PieceValue.WHITE;
				break;
			default:
				throw new RuntimeException();
		};
		return gameService.read(payloadObject.getName(), pieceValue);
	}

	@Data
	public static final class Payload {
		private String name;
		private String piece;
	}
}
