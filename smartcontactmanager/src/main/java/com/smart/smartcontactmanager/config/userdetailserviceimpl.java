package com.smart.smartcontactmanager.config;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.smartcontactmanager.entities.User;
import com.smart.smartcontactmanager.repository.Userrepository;

public class userdetailserviceimpl implements UserDetailsService {

/**
 *  here we set user to userdetail
 * 
 * 
 */

	@Autowired
	private Userrepository userrepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// fetching user from database	
			User user = userrepo.getUserByUserName(username);
			
			if(user==null)
			{
				throw new  UsernameNotFoundException("username not found");
			}
					 userdetailsimple userdetail = new	userdetailsimple(user);
			
			return userdetail;
		
		
	
	}
}
