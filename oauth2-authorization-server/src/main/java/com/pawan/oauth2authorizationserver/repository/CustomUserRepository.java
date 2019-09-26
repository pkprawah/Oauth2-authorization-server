/**
 * 
 */
package com.pawan.oauth2authorizationserver.repository;


import com.pawan.oauth2authorizationserver.model.User;

/**
 * @author Pawan.kumar
 *
 */

public interface CustomUserRepository  {
	
	User getUserByUsername(String username);
	
}
