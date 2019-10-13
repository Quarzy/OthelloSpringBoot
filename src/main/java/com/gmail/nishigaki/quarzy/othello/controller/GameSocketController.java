package com.gmail.nishigaki.quarzy.othello.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.nishigaki.quarzy.othello.model.GameResult;

/**
 * @author nishigaki
 */
@RestController
public interface GameSocketController {

//	@MessageMapping("/game")
////	@SendToUser("/callback/assigned")
//	public void game(Principal principal, @Payload String payload);
//
//	@MessageMapping("/game2")
//	@SendToUser("/queue/assigned")
//	public String game2(Principal principal, @Payload String payload);

	@MessageMapping("/game3")
	@SendTo("/topic/game3")
	public String game(@Payload String payload);
}
