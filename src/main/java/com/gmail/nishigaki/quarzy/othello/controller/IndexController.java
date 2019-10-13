package com.gmail.nishigaki.quarzy.othello.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nishigaki
 */
@RequestMapping("/")
public interface IndexController {

	@GetMapping
	public String get(Model model, HttpSession session);
}
