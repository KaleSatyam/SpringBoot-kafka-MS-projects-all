package com.blogapplication.Blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blogapplication.Blog.exceptions.ApiException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	Logger logger=LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private UserDetailService customuserdetailservice;
	
	@Autowired
	private JwtTokenHelper tokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	String requestToken=request.getHeader("Authorization");
	System.out.println("Token  " +requestToken);
	
	String username=null;
	String Token=null;
	
	if(requestToken!=null && requestToken.startsWith("Bearer")) {
		Token=requestToken.substring(7);
		try {
		username = this.tokenHelper.getuserNameFromToken(Token);
		}catch (IllegalArgumentException e) {
			logger.info("Unable to get JWT Token");
			throw new  ApiException("Unable to get JWT Token");
		}catch (ExpiredJwtException e) {
			logger.info("JWT Tokan has expired");
			throw new  ApiException("JWT Tokan has expired");
		}catch (MalformedJwtException e) {
			logger.info("Invalid JWT Tokan");
			throw new  ApiException("Invalid JWT Tokan");
		}
		
	}else {
		logger.info("JWT token dose not begin with Bearer");
	}
	
	//once we get token then validate it
	if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		UserDetails userdetails = this.customuserdetailservice.loadUserByUsername(username);
		if(this.tokenHelper.validateToken(Token, userdetails)) {
			//if token valid then do validation authentication
			UsernamePasswordAuthenticationToken authenticationtoken=new UsernamePasswordAuthenticationToken(userdetails,null, userdetails.getAuthorities());
			authenticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(authenticationtoken);
			
		}else {
			logger.info("Invalid Jwt Token");
		}
		
	}else {
		logger.info("username is null or context is null");
	}
	filterChain.doFilter(request, response);
	}

}
