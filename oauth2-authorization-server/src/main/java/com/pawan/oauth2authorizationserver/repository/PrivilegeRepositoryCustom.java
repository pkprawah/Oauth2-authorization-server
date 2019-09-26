package com.pawan.oauth2authorizationserver.repository;

import org.springframework.stereotype.Repository;

import com.pawan.oauth2authorizationserver.model.Privilege;



@Repository
public interface PrivilegeRepositoryCustom {

	Privilege getPrivilegeByPrivilegeName(String privilegeName);
	

}
