package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.chat_application;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/chat")
@CrossOrigin
public class WebSocketController {
	
	@MessageMapping("/user-all")
	@SendTo("/topic/user")
	public MessageBean sendToAll(@Payload MessageBean message) {
		return message;
	}
	
}
