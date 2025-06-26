package com.blogapplication.Blog.services;

import java.util.List;

import com.blogapplication.Blog.models.Post;
import com.blogapplication.Blog.payloads.PostDto;
import com.blogapplication.Blog.payloads.PostResponse;

public interface PostService {

	PostDto createpost(PostDto dto,Long userid,Long categoryid);
	
	PostDto  updatepost(PostDto dto,Long id);
	
	void deletepost(Long id);
	
	PostDto getpost(Long id);
	
	PostResponse getallpost(int pagenumber,int pagesize,String Sortby,String sortdir);
	
	List<PostDto> getpostbycategory(Long id);
	
	List<PostDto> getpostbyuser(Long id);
	
	List<PostDto> searchpost(String keyword);
}
