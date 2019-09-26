package com.pawan.oauth2authorizationserver.repository;


import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.pawan.oauth2authorizationserver.model.Role;


@Repository
public interface CustomRoleRepository {
	
	Role getRoleByRoleName(String roleName);
	
	List<Role> getRoleByUsername(String username);
	
	
	

}
