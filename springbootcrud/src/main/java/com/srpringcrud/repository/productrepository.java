package com.srpringcrud.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.srpringcrud.entity.Product;

public interface productrepository extends JpaRepository<Product, Integer> {


}
