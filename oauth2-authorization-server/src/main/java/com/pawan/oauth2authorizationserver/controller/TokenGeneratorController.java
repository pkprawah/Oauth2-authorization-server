package com.pawan.oauth2authorizationserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.oauth2authorizationserver.model.User;

@RestController
@RequestMapping("/generate")
public class TokenGeneratorController {
	
	
	/*@Autowired
	private JWTGenerator jwtGenerator;*/
	
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public String generator(@RequestBody User user){
		 System.out.print("call LoginController -> signIn +12 ");
		 return null;//jwtGenerator.generate(user);
		  
	}

}
