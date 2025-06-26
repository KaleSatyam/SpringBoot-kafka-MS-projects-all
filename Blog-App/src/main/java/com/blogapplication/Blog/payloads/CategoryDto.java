package com.blogapplication.Blog.payloads;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blogapplication.Blog.models.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Long categoryid;

	@NotEmpty
	@Size(min = 3,message = "Add minimum 3 characters")
	private String categotyTitle;
	
	@NotEmpty
	@Size(min = 10,message = "Add minimum 10 characters")
	private String categorydescription;
//	private Set<Post> post=new HashSet<Post>();
}
