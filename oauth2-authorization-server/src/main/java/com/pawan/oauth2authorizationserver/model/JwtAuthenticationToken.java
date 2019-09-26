package com.pawan.oauth2authorizationserver.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String token;

	public JwtAuthenticationToken(String token) {
		
		super(null, null);
		this.token = token;
		System.out.println("authenticationtoken is ::"+token);
	}

	public String getToken() {
		System.out.println(" return authenticationtoken token is  ::"+token);
		return token;
	}

	public void setToken(String token) {
		System.out.println(" setting authenticationtoken is ::"+token);
		this.token = token;
	}

}
