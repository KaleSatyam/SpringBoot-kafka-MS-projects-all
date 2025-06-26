package com.blogapplication.Blog.controllers;

import javax.validation.Valid;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.Blog.payloads.ApiResponse;
import com.blogapplication.Blog.payloads.UserResponse;
import com.blogapplication.Blog.payloads.Userdto;
import com.blogapplication.Blog.services.userservice;
import com.blogapplication.Blog.utils.Constants;

@RestController
@RequestMapping("/api/users/")
public class Usercontroller {
	
	@Autowired
	private userservice userservice;
	
//post user 
	
	@PostMapping("/")
	public ResponseEntity<Userdto> createuser(@Valid @RequestBody Userdto user){
		
		System.out.println("Data : "+user.toString());
		Userdto createuser = userservice.createuser(user);
		
		return new ResponseEntity<>(createuser,HttpStatus.CREATED);
		
	}
	
	//getallusers
	
	@GetMapping("/")
	public ResponseEntity<UserResponse> getallusers(
			@RequestParam(name  = "pagenumber",defaultValue = Constants.pagenumber,required = false) int pagenumber,
			@RequestParam(name = "pagesize",defaultValue = Constants.pagesize,required = false) int pagesize,
			@RequestParam(value = "sortby", defaultValue = Constants.user_sortby,required = false) String sortby,
			@RequestParam(value = "sortdir", defaultValue = Constants.sort_direction,required = false) String sortdir){
		UserResponse getalluser = this.userservice.getalluser(pagenumber, pagesize, sortby, sortdir);
		
		return new ResponseEntity<>(getalluser,HttpStatus.OK);
		
	}
	
	//getuserby id
	
	@GetMapping("/{id}")
	public ResponseEntity<Userdto> getuserbyid(@PathVariable("id") long id){
		System.out.println("get by id  "+ id);
		Userdto getuserbyid = this.userservice.getuserbyid(id);

		return new ResponseEntity<>(HttpStatus.OK).ok(getuserbyid);
		
	}
	
	//update user
	
	@PutMapping("/{id}")
	public ResponseEntity<Userdto> updateuser(@Valid @PathVariable("id") long id,
			@RequestBody Userdto user ){
		System.out.println("update by id  "+ id);
		System.out.println("update body   "+ user);
		Userdto updated = this.userservice.updateeuser(user, id);
		
		return new ResponseEntity<>(updated,HttpStatus.CREATED);
	}
	
	//delete user
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteuser(@PathVariable("id") long id){
		this.userservice.Deleteuser(id);
		
		return new ResponseEntity<>(new ApiResponse("Deleted user", true),HttpStatus.OK);
		
	}

}
