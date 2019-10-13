package com.gmail.nishigaki.quarzy.othello.controller.impl;

import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.gmail.nishigaki.quarzy.othello.controller.InitController;
import com.gmail.nishigaki.quarzy.othello.model.GameInitResult;
import com.gmail.nishigaki.quarzy.othello.model.GameReadResult;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import com.gmail.nishigaki.quarzy.othello.service.GameService;

/**
 * @author nishigaki
 */
@Controller
public class InitControllerImpl implements InitController {

	@Autowired
	private GameService gameService;

	@Override
	public final String init(final Model model, final HttpSession session) {
		final GameInitResult result = gameService.init();
//		session.setAttribute("name", result.getName());
		session.setAttribute("piece", result.getPieceValue());
//		model.addAttribute("player", true);
//		model.addAttribute("name", result.getName());
//		model.addAttribute("piece", result.getPieceValue());
//		model.addAttribute("board", result.getBoard());
		return "redirect:/games/" + result.getName() + "?piece=" + result.getPieceValue();
	}

	@Override
	public final String init(final Model model, final HttpSession session,
			final String name, final Optional<PieceValue> pieceValue) {
		final GameReadResult result = gameService.read(name, pieceValue.orElse(PieceValue.WHITE));
		model.addAttribute("player", result.isPlayer());
		model.addAttribute("name", name);
		model.addAttribute("piece", result.getPieceValue());
		model.addAttribute("board", result.getBoard());
		return "game";
	}
}
