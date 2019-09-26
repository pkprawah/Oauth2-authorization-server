package com.pawan.oauth2authorizationserver.configure;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Authorization, Content-Type, Accept");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
        	filterChain.doFilter(servletRequest, servletResponse);
        }
		
	}
	
	public void destroy() {
    }

}
