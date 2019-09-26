package com.pawan.oauth2authorizationserver.configure;



import org.apache.tomcat.jdbc.pool.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.handioq.diber")
@EnableAutoConfiguration
public class Application {
	
	
	/*@Value("${spring.datasource.driverClassName}") //spring.datasource.driverClassName = com.mysql.jdbc.Driver
    private String databaseDriverClassName;*/
		
		@Value("${spring.jpa.properties.hibernate.dialect}")
	    private String databaseDialect;

	    @Value("${spring.datasource.url}")
	    private String datasourceUrl;

	    @Value("${spring.datasource.username}")//spring.datasource.username
	    private String databaseUsername;

	    @Value("${spring.datasource.password}")//spring.datasource.password
	    private String databasePassword;

	    public static void main(String[] args) {
	        SpringApplication.run(Application.class, args);
	    }

	    @Bean
	    public DataSource dataSource() {
	        DataSource dataSource = new DataSource();
	        dataSource.setDriverClassName(databaseDialect);
	        dataSource.setUrl(datasourceUrl);
	        dataSource.setUsername(databaseUsername);
	        dataSource.setPassword(databasePassword);

	        return dataSource;
	    }
}
