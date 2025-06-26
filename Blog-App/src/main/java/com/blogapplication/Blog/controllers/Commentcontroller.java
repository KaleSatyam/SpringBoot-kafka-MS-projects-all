package com.blogapplication.Blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.Blog.payloads.ApiResponse;
import com.blogapplication.Blog.payloads.CommentDto;
import com.blogapplication.Blog.services.commentservice;

@RestController
@RequestMapping("/api/")
public class Commentcontroller {

	@Autowired
	private commentservice commentservice;
	
	
	@PostMapping("/post/{postid}/user/{userid}/comment")
	public ResponseEntity<CommentDto> createcomment(@RequestBody CommentDto commentdto,@PathVariable Long postid,@PathVariable Long userid)
	{
		CommentDto createcomment = this.commentservice.createcomment(commentdto, postid, userid);
		
		return new ResponseEntity<>(createcomment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentid}")
	public ResponseEntity<ApiResponse> createcomment(@PathVariable Long commentid)
	{
		 this.commentservice.deletecomment(commentid);
		return new ResponseEntity<>(new ApiResponse("Comment deleted ", true),HttpStatus.CREATED);
	}
	
	

}
