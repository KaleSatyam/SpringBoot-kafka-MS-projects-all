package com.blogapplication.Blog.reprsitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapplication.Blog.models.User;

@Repository
public interface userrepo extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
}
