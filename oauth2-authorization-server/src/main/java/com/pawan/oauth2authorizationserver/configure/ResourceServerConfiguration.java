package com.pawan.oauth2authorizationserver.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;



@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

	
	//private static final String RESOURCE_ID = "restservice";
	
	
	 /*@Override
     public void configure(ResourceServerSecurityConfigurer resources) {
         resources
                 .resourceId(RESOURCE_ID);
     }*/

     @Override
     public void configure(HttpSecurity http) throws Exception {
         http
                 .authorizeRequests()
                 .antMatchers("/employee").hasRole("ADMIN")
                // .antMatchers("/api/**").hasAnyRole("ADMIN")
                 .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());//, "CUSTOMER", "COURIER")
                 //.antMatchers("/greeting").authenticated();
                // .antMatchers("/greeting").hasRole("ADMIN");
     }
}
