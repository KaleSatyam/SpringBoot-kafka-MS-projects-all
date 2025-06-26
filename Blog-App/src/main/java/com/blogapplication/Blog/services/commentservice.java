package com.blogapplication.Blog.services;

import com.blogapplication.Blog.payloads.CommentDto;

public interface commentservice {
	
	CommentDto createcomment(CommentDto dto,Long id,Long userid);
	
	void deletecomment(Long id);

}
