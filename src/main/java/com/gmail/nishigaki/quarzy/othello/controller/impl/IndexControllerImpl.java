package com.gmail.nishigaki.quarzy.othello.controller.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.gmail.nishigaki.quarzy.othello.controller.IndexController;
import com.gmail.nishigaki.quarzy.othello.service.GameService;

/**
 * @author nishigaki
 */
@Controller
public class IndexControllerImpl implements IndexController {

	@Autowired
	private GameService gameService;

	@Override
	public String get(final Model model, final HttpSession session) {
//		final Object name = session.getAttribute("name");
//		if (name != null) {
//			return "redirect:/games/" + name;
//		}
		model.addAttribute("names", gameService.readNames());
		return "index";
	}
}
