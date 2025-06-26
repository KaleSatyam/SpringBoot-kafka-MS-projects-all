package com.blogapplication.Blog.reprsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapplication.Blog.models.Categories;

@Repository
public interface CategoryRepo extends JpaRepository<Categories,Long> {

}
