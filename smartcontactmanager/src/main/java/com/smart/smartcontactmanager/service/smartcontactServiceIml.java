package com.smart.smartcontactmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.smartcontactmanager.entities.User;
import com.smart.smartcontactmanager.repository.Userrepository;

@Service
public class smartcontactServiceIml implements smartcontactService {

	@Autowired
	private Userrepository  userrepo;
	
	@Override
	public User saveuser(User data) {
		
		User save = userrepo.save(data);
		return save;
	}

}
