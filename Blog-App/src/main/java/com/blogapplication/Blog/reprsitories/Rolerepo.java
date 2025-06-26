package com.blogapplication.Blog.reprsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapplication.Blog.models.Role;

@Repository
public interface Rolerepo extends JpaRepository<Role, Long> {

}
