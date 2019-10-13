package com.gmail.nishigaki.quarzy.othello.controller.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.nishigaki.quarzy.othello.controller.GameController;
import com.gmail.nishigaki.quarzy.othello.model.GameResult;
import com.gmail.nishigaki.quarzy.othello.model.Piece;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import com.gmail.nishigaki.quarzy.othello.service.GameService;

/**
 * @author nishigaki
 */
@RestController
public class GameControllerImpl implements GameController {

	@Autowired
	private GameService gameService;

	@Override
	public final ResponseEntity<GameResult> assign(final Position position, final HttpSession session) {
//		final Object name = session.getAttribute("name");
//		if (name == null) {
//			throw new RuntimeException();
//		}
//		final PieceValue piece = PieceValue.valueOf(session.getAttribute("piece").toString());
		final GameResult result = gameService
				.replace(position.getName(), new Piece(position.getPiece(), position.getX(), position.getY()));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
