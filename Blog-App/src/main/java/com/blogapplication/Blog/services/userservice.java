package com.blogapplication.Blog.services;


import com.blogapplication.Blog.payloads.UserResponse;
import com.blogapplication.Blog.payloads.Userdto;

public interface userservice {
	
	Userdto registerUser(Userdto userdto);
	Userdto createuser(Userdto user);
	Userdto updateeuser(Userdto user,Long id);
	UserResponse  getalluser(int pagenumber,int pagesize,String sortby,String sortdir);
	Userdto getuserbyid(Long id);
	void Deleteuser(Long id);
	
	

}
