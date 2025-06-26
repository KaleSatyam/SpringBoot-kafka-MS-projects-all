package com.blogapplication.Blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapplication.Blog.exceptions.ResourceNotFoundException;
import com.blogapplication.Blog.models.User;
import com.blogapplication.Blog.reprsitories.userrepo;

@Service
public class UserDetailService  implements UserDetailsService{
	
	@Autowired
	private userrepo userrepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userrepo.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("Username", "Username: "+username, 0));
		return user;
	}

}
