package com.blogapplication.Blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.Blog.exceptions.ApiException;
import com.blogapplication.Blog.payloads.JwtAuthRequest;
import com.blogapplication.Blog.payloads.JwtAuthResponse;
import com.blogapplication.Blog.payloads.Userdto;
import com.blogapplication.Blog.security.JwtTokenHelper;
import com.blogapplication.Blog.security.UserDetailService;
import com.blogapplication.Blog.services.userservice;

@RestController
@RequestMapping("/api/v1/auth/")
public class authController {

	private JwtTokenHelper jwttokenhelper;
	
	private UserDetailService userdetailservice;
	
	private AuthenticationManager authenticationmaneger;
	
	@Autowired
	private userservice userservice;
	
	@Autowired
	public authController(JwtTokenHelper jwttokenhelper, UserDetailService userdetailservice,
			AuthenticationManager authenticationmaneger) {
		super();
		this.jwttokenhelper = jwttokenhelper;
		this.userdetailservice = userdetailservice;
		this.authenticationmaneger = authenticationmaneger;
	}


	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createtoken(
			@RequestBody JwtAuthRequest request) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userdetail = this.userdetailservice.loadUserByUsername(request.getUsername());
		String generateToken = this.jwttokenhelper.generateToken(userdetail);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(generateToken);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Userdto> createuser(@Valid @RequestBody Userdto user){
		
		System.out.println("Data : "+user.toString());
		Userdto createuser = this.userservice.registerUser(user);
		
		return new ResponseEntity<>(createuser,HttpStatus.CREATED);
		
	}



	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken usernamepasswordauthenticationtoke=new UsernamePasswordAuthenticationToken(username, password);

		try {
			this.authenticationmaneger.authenticate(usernamepasswordauthenticationtoke);
		} catch (BadCredentialsException e) {
			throw  new ApiException("Invalid Username or Password");
			
		}
	}
}
