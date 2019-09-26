package com.pawan.oauth2authorizationserver.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pawan.oauth2authorizationserver.model.User;
import com.pawan.oauth2authorizationserver.repository.UserRepository;
import com.pawan.oauth2authorizationserver.service.LoginService;
import com.pawan.oauth2authorizationserver.util.APIResponse;

@Service
@Transactional
@Component
public class LoginServiceImp implements LoginService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public APIResponse signIn(String username, String password) {
		User usr=userRepository.getUserByUsername(username);
		APIResponse apiResponse=new APIResponse();
		System.out.println("User details in services ::"+ usr);
		if(usr!=null){
			apiResponse.setResponseStatus("200");
			apiResponse.setResponseMessage("Success");
			apiResponse.setResponseData(true);
			return apiResponse;
		}
		apiResponse.setResponseStatus("405");
		apiResponse.setResponseMessage("Failed");
		apiResponse.setResponseData(false);
		return apiResponse;
		
		
	}

}
