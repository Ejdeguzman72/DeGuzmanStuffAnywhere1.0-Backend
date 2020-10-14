package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.authentication_controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
@CrossOrigin
public class UsernameSecurityController {
	
	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public String getUsernameFromToken(Principal principal) {
		return principal.getName();
	}
	
}
