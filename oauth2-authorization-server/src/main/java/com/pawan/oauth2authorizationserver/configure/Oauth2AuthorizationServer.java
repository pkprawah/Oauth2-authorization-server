package com.pawan.oauth2authorizationserver.configure;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import ch.qos.logback.core.encoder.Encoder;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServer implements AuthorizationServerConfigurer{

	
	
	static final String CLIEN_ID = "quora-client";
	static final String CLIENT_SECRET = "quora-secret";
	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
	
	
   @Autowired
    private static PasswordEncoder encoder;
    
	@Autowired
    //@Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

   /* @Autowired
    private UserDetailsServiceCustom userDetailsService;
*/
   /* @Autowired
    Application dataSource;*/
	
	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
		
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		 clients.inMemory().withClient("sampleClientId").authorizedGrantTypes("implicit")
		 .scopes("read", "write", "foo", "bar").autoApprove(false)
		 .accessTokenValiditySeconds(3600)
		 .redirectUris("http://localhost:8083/","http://localhost:8086/")
		 .and()
		 .withClient("fooClientIdPassword").secret(passwordEncoder().encode("secret"))
		 .authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials")
		 .scopes("foo", "read", "write").accessTokenValiditySeconds(3600)
         // 1 hour
         .refreshTokenValiditySeconds(2592000)
         // 30 days
         .redirectUris("xxx","http://localhost:8089/",
        		 "http://localhost:8080/login/oauth2/code/custom",
        		 "http://localhost:8080/ui-thymeleaf/login/oauth2/code/custom",
        		 "http://localhost:8080/authorize/oauth2/code/bael", 
        		 "http://localhost:8080/login/oauth2/code/bael")
         .and()
         .withClient("barClientIdPassword").secret(passwordEncoder().encode("secret"))
         .authorizedGrantTypes("password", "authorization_code", "refresh_token")
         .scopes("bar", "read", "write").accessTokenValiditySeconds(3600)
         // 1 hour
         .refreshTokenValiditySeconds(2592000) // 30 days
         .and().withClient("testImplicitClientId")
         .authorizedGrantTypes("implicit").scopes("read", "write", "foo", "bar")
         .autoApprove(true).redirectUris("xxx");

		
		
		
		
		
		
		/*clients
        .jdbc(dataSource.dataSource())
        .passwordEncoder(passwordEncoder())
        .withClient("sampleClientId")
        .authorizedGrantTypes("implicit")
        .scopes("read")
        .autoApprove(true)
        .and()
        .withClient("clientIdPassword")
        .secret("secret")
        .authorizedGrantTypes("password","authorization_code", "refresh_token")
        .scopes("read");
        */
        

        /*
        .withClient(CLIENT) // uncomment this if we want to create new client with name and secret
        .authorizedGrantTypes(GRANT_TYPE_PASSWORD, GRANT_TYPE_REFRESH_TOKEN)
        .authorities(AUTHORITY_USER)
        .scopes(SCOPE_READ, SCOPE_WRITE)
        .resourceIds(RESOURCE_ID)
        .secret(SECRET);
        */
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain).authenticationManager(authenticationManager);
		
		
		
		
		/* endpoints.tokenStore(tokenStore())
			.authenticationManager(authenticationManager)
            .accessTokenConverter(accessTokenConverter());
		 
		 */
		 
         /*.tokenStore(new JdbcTokenStore(dataSource.dataSource()))
         .authenticationManager(this.authenticationManager)
         .userDetailsService(userDetailsService);*/
         //.tokenServices(tokenService());
		
	}
	
	
	 @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(accessTokenConverter());
	    }
	 
	 
	 @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        converter.setSigningKey("123");
	        // final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
	        // converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
	        return converter;
	    }
	 
	 @Bean
	    public TokenEnhancer tokenEnhancer() {
	        return new CustomTokenEnhancer();
	    }
	
	 
	 @Bean
	    @Primary
	    public DefaultTokenServices tokenServices() {
	        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	        defaultTokenServices.setTokenStore(tokenStore());
	        defaultTokenServices.setSupportRefreshToken(true);
	        return defaultTokenServices;
	    }
	 
	 
	 @Bean
     public PasswordEncoder passwordEncoder() {
         if(encoder == null) {
             encoder = new BCryptPasswordEncoder();
         }
         return encoder;
     }
	
	/*@Bean
    @Primary
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
        tokenServices.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
        tokenServices.setTokenStore(new JdbcTokenStore(dataSource.dataSource()));
        return tokenServices;
    }*/
	
	 
	 
	 
	 /*@Bean
	    public TokenStore tokenStore() {
	        return new JdbcTokenStore(dataSource.dataSource());
	    }
	 */
	 
	 /*@Primary
	 @Bean
	 public RemoteTokenServices tokenService() {
	     RemoteTokenServices tokenService = new RemoteTokenServices();
	     tokenService.setCheckTokenEndpointUrl(
	       "http://localhost:8080/spring-security-oauth-server/oauth/check_token");
	     tokenService.setClientId("fooClientIdPassword");
	     tokenService.setClientSecret("secret");
	     return tokenService;
	 }*/
	 
	 
	 
	 
	

}
