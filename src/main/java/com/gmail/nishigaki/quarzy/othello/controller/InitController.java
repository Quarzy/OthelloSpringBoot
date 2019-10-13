package com.gmail.nishigaki.quarzy.othello.controller;

import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
@RequestMapping("/games")
public interface InitController {

	@PostMapping
	public String init(Model model, HttpSession session);

	@RequestMapping("/{name}")
	@GetMapping
	public String init(Model model, HttpSession session,
			@PathVariable("name") String name,
			@RequestParam(name = "piece", required = false) Optional<PieceValue> pieceValue);
}
