package com.blogapplication.Blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blogapplication.Blog.models.Comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDto {
	
	
	private Long pid;
	@NotEmpty
	@Size(min = 5,message = "Min size is 5 character")
	private String title;
	@NotEmpty
	@Size(min = 15,message = "Min size is 25 character")
	private String content;
	private String image;
	private Date adddateDate;
	private CategoryDto categories;
	private Userdto user;
	private Set<CommentDto> comment=new HashSet<>();

}
