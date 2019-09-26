package com.pawan.oauth2authorizationserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.oauth2authorizationserver.service.RecordLoaderService;
import com.pawan.oauth2authorizationserver.util.APIResponse;

@RestController
@RequestMapping("/initial")
public class InitialDataLoaderController {
	
	
	
	@Autowired
	private RecordLoaderService recordLoaderService;
	
	 @RequestMapping(value = "/dataloader", method = RequestMethod.GET)
	public APIResponse saveInitialData(){
		 System.out.println(" :: Called InitialDataLoaderController ::");
		 System.out.println("Object value of recordLoaderService ::"+recordLoaderService);
		 recordLoaderService.insertRecord();
		 APIResponse apiResponse=new APIResponse();
		 apiResponse.setResponseStatus("200");
		 apiResponse.setResponseMessage("save successfully");
		return apiResponse ;
	}
	
	
}
