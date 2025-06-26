package com.blogapplication.Blog.payloads;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blogapplication.Blog.models.Comment;
import com.blogapplication.Blog.models.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userdto {
	
	private long userid;
	@NotEmpty
	private String name;
	@javax.validation.constraints.Email(message = "Email address is not valid !!")
	private String email;
	private String address;
	@NotEmpty
	@Size(min = 5,message = "UserName must be min of 4 characters !!")
	private String username;
//	@JsonIgnore
	@NotEmpty
	@Size(min = 5,message = "Password should be min 5 characters !!")
	private String password;
	
//	private Set<PostDto> post=new HashSet<>();
	private Set<CommentDto> comment=new HashSet<>();

}
