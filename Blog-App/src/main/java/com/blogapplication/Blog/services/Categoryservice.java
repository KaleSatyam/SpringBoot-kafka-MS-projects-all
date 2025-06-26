package com.blogapplication.Blog.services;


import com.blogapplication.Blog.payloads.CategoryDto;
import com.blogapplication.Blog.payloads.CategoryResponse;

public interface Categoryservice {
	
	CategoryDto createcategory(CategoryDto dto);
	
	CategoryDto updatecategory(CategoryDto dto,long id);
	
	void deletecategory(long id);
	
	CategoryDto getcategory(long id);
	
	CategoryResponse getallcategory(int pagenumber,int pagesize,String sortby,String sortdir);
	
	

}
