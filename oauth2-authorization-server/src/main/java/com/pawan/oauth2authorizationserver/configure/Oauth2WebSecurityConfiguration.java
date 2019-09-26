package com.pawan.oauth2authorizationserver.configure;

import javax.annotation.PostConstruct;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import com.pawan.oauth2authorizationserver.security.JwtAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Oauth2WebSecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	/*@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;*/
	@Autowired
	private WebApplicationContext applicationContext;
	@Autowired
	private UserDetailsServiceCustom userDetailsService;
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.authenticationProvider(authenticationProvider)
		.userDetailsService(this.userDetailsService).passwordEncoder(encoder());
	}
	
  
	
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	    	 System.out.print("call configure -> WebSecurity  +3 ");
	        web.ignoring().antMatchers("/resources/**");
	    }
	 
	 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@PostConstruct
    public void setupCustomSuccessHandler() {
	 System.out.print("call setupCustomSuccessHandler  ");
		//customSuccessHandler =applicationContext.getBean(CustomSuccessHandler.class);
}
	
	 @PostConstruct
	    public void setupInitialization() {
		 System.out.print("call setupInitialization +1 ");
	        userDetailsService =applicationContext.getBean(UserDetailsServiceCustom.class); 
	        System.out.print("UserDetailsServiceCustom userDetailsService ::" +userDetailsService);
	}
	 	
	 	@Autowired
	    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService)
	                .passwordEncoder(encoder());
	    }

	    @Override
	    protected void configure(final HttpSecurity httpSecurity) throws Exception {
	    	System.out.print("call configure -> HttpSecurity +4 ");
	    	
	    	httpSecurity.authorizeRequests().antMatchers("/login").permitAll()
			.antMatchers("/oauth/token/revokeById/**").permitAll()
			.antMatchers("/tokens/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.and().csrf().disable();
	    	
	    	
	    	//httpSecurity
			//.csrf().disable().authorizeRequests()
               // .antMatchers("**/rest/secure/**").authenticated();
    	
	}
	    	

	    @Bean
	    public PasswordEncoder encoder() {
	    	System.out.print("call PasswordEncoder -> +6 ");
	        return new BCryptPasswordEncoder();
	    }
	    
	    
	    
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		@Bean
	    public FilterRegistrationBean corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        source.registerCorsConfiguration("/**", config);
			FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter());
	        bean.setOrder(0);
	        return bean;
	    }
	   

	   
}
