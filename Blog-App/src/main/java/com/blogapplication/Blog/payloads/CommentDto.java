package com.blogapplication.Blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter@Setter@AllArgsConstructor
public class CommentDto {
	
	
	private long cid;
	private String content;
//	private PostDto post;
//	private Userdto user;

}
