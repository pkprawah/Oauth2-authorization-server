package com.pawan.oauth2authorizationserver.service;

import java.util.List;

import com.pawan.oauth2authorizationserver.model.User;


public interface UserService {

	List<User> findAll();
}
