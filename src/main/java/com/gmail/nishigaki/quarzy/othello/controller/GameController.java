package com.gmail.nishigaki.quarzy.othello.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.nishigaki.quarzy.othello.model.GameResult;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import lombok.Data;

/**
 * @author nishigaki
 */
@RequestMapping("/game/assign")
@RestController
public interface GameController {

	@PostMapping
	public ResponseEntity<GameResult> assign(@RequestBody Position position, HttpSession session);

	@Data
	public final class Position {
		private String name;
		private PieceValue piece;
		private int x;
		private int y;
	}
}
