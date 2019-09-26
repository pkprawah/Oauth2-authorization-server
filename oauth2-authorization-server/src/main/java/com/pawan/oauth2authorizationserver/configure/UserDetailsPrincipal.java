package com.pawan.oauth2authorizationserver.configure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pawan.oauth2authorizationserver.model.Role;
import com.pawan.oauth2authorizationserver.model.User;

public class UserDetailsPrincipal extends User implements UserDetails{

	private String username;
	private Long userId;
	private String token;
	private List<GrantedAuthority> authorities;
	
	 private  User user;
	
	public UserDetailsPrincipal(String username, Long userId, String token,
			List<GrantedAuthority> authorities) {
		System.out.print("username ::"+username+"userId :"+userId+"token :"+token+"authorities :"+authorities +" in the UserDetailsPrincipal constructor");// -> UserDetailsPrincipal +9 ");
		this.username = username;
		this.userId = userId;
		this.token = token;
		this.authorities = authorities;
	}

	public UserDetailsPrincipal(User user) {
		
		super(user);
		System.out.print("call UserDetailsPrincipal -> UserDetailsPrincipal +9 ");
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 System.out.print("call UserDetailsPrincipal -> getAuthorities +11 ");
		//List<Role> roleList=(List<Role>)super.getRoles();
		//System.out.print("Role List :::"+roleList);
		//authorities=new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority simpleGrantedAuthority=null;
		//String roleName=null;
		//for(Role role :roleList){
			//roleName=role.getRoleName();
			//simpleGrantedAuthority=new SimpleGrantedAuthority(roleName);//"ROLE_"+
			// authorities.add(simpleGrantedAuthority);
			
			
		//}
		//System.out.println("role Name:"+roleName);
		  /*List<GrantedAuthority> authorities = Collections.singletonList(
				 new SimpleGrantedAuthority("ROLE_"+roleName));*/
		 System.out.print("GrantedAuthority Authorities for token case :::"+authorities);
		 return authorities;
	}

	@Override
	public String getPassword() {
		 return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	boolean isAuthenticated(){
		return true;
	}

}
