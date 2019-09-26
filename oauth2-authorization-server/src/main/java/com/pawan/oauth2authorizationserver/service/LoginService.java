package com.pawan.oauth2authorizationserver.service;

import com.pawan.oauth2authorizationserver.util.APIResponse;

public interface LoginService {
	
	public APIResponse signIn(String username,String password);

}
