package com.pawan.oauth2authorizationserver.security;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.pawan.oauth2authorizationserver.configure.UserDetailsPrincipal;
import com.pawan.oauth2authorizationserver.model.JwtAuthenticationToken;
import com.pawan.oauth2authorizationserver.model.Role;
import com.pawan.oauth2authorizationserver.model.User;


@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	/*@Autowired
	private JwtTokenValidator jwtTokenValidator;
	*/
	
	
	@Override
	public boolean supports(Class<?> authentication) {
		//This is JwtAuthenticationToken the model class which extends the usernamepasswordauthenticationtoken
		//which is used by the other class as model.
		
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
			throws AuthenticationException {
		
		JwtAuthenticationToken jwtAuthenticationToken =(JwtAuthenticationToken)usernamePasswordAuthenticationToken;
		String token=jwtAuthenticationToken.getToken();
		User user=null;//jwtTokenValidator.validate(token);
		
		System.out.println("UserDetails in retrieveUser::" +user);
		
		if(user==null){
			throw new RuntimeException("JWT token is incorrect");
		}
				System.out.print("call UserDetailsPrincipal -> getAuthorities -> JwtAuthenticationProvider");
				List<Role> roleList=(List<Role>)user.getRoles();
				System.out.print("Role List :::"+roleList);
				List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
				SimpleGrantedAuthority simpleGrantedAuthority=null;
				String roleName=null;
				for(Role role :roleList){
					roleName=role.getRoleName();
					simpleGrantedAuthority=new SimpleGrantedAuthority(roleName);//"ROLE_"+
					 authorities.add(simpleGrantedAuthority);
					
					
				}

		System.out.println("List<GrantedAuthority> authorities ::: "+authorities);
		
		String passingusername=user.getUsername();
		Long passinguserId=user.getUserId();
		System.out.println("passingusername  value" +passingusername +" "+"and passinguserId value is  :: "+passinguserId);
		UserDetailsPrincipal userDetailsPrincipal=
				new UserDetailsPrincipal(passingusername,passinguserId,token,authorities);
		
		userDetailsPrincipal.setUsername(passingusername);
		userDetailsPrincipal.setUserId(passinguserId);
		userDetailsPrincipal.setToken(token);
		userDetailsPrincipal.setAuthorities(authorities);
		
		System.out.println("Returning value for userDetailsPrincipal :: "+userDetailsPrincipal);
		
		return userDetailsPrincipal;
	}

}
