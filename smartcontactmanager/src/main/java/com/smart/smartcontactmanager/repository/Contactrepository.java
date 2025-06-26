package com.smart.smartcontactmanager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.smartcontactmanager.entities.Contact;
import com.smart.smartcontactmanager.entities.User;

public interface Contactrepository extends JpaRepository<Contact, Integer>{
	
	public Page<Contact> findAllByUser(User user,Pageable pageable);
	
	
	

}
