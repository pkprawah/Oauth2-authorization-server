package com.pawan.oauth2authorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class EmployeeController {

	 @GetMapping("/employee")
	    @ResponseBody
	    public String getEmployee() {
	        return "Employee details:Pawan Kumar";
	    }
}
