package com.pawan.oauth2authorizationserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.pawan.oauth2authorizationserver.service.UserService;

public class Oauth2UserController {
	
	
	@Autowired
    private UserService userService;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List listUser(){
        return userService.findAll();
    }


}
