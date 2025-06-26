package com.blogapplication.Blog.reprsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapplication.Blog.models.Comment;

@Repository
public interface Commentrepor extends JpaRepository<Comment, Long>{

}
