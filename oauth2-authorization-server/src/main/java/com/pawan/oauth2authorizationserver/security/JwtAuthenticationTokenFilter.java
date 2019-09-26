package com.pawan.oauth2authorizationserver.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pawan.oauth2authorizationserver.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	
	
/*	public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
	    this.authenticationManager = authenticationManager;
	    this.tokenService= ctx.getBean(TokenService.class);
	}
	
	*/
	
	
	//AbstractAuthenticationProcessingFilter
	
	 //UsernamePasswordAuthenticationFilter
	//@Autowired
	//private AuthenticationManager authenticationManager;
	//private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

	/*public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		
		this.authenticationManager=authenticationManager;
		
	}
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
    this.authenticationManager = authenticationManager;
    this.tokenService= ctx.getBean(TokenService.class);
}

	public void setAuthenticationSuccessHandler(JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler) {
		
		this.jwtAuthenticationSuccessHandler=jwtAuthenticationSuccessHandler;
	}*/

	// This will authenticate the token for all the url.
	public JwtAuthenticationTokenFilter() {
		super("/rest/secure/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws AuthenticationException, IOException, ServletException {
		
		String header=httpServletRequest.getHeader("authorization");
		
		if(header==null || !header.startsWith("Token")){
			throw new RuntimeException("Token is missing please provide the correct token");
		}
		
		String authenticationToken=header.substring(6);
		
		System.out.println("::Token ::  "+authenticationToken);
		
		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
		
		System.out.println("::JwtAuthenticationToken class ::"+token);
		
		System.out.println("::getAuthenticationManager() ::"+getAuthenticationManager());
		return getAuthenticationManager().authenticate(token);
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
		}

}
