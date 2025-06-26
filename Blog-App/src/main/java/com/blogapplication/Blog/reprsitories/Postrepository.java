package com.blogapplication.Blog.reprsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapplication.Blog.models.Categories;
import com.blogapplication.Blog.models.Post;
import com.blogapplication.Blog.models.User;

@Repository
public interface Postrepository extends JpaRepository<Post, Long>{
	
	List<Post> findAllByUser(User user);
	List<Post> findAllByCategories(Categories categories);
	List<Post> findByTitleContaining(String title);
	

}
