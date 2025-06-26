package com.srpringcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srpringcrud.entity.Product;
import com.srpringcrud.repository.productrepository;

@Service
public class productservice  {

	@Autowired
	productrepository prepo;
	
	public Product saveproduct(Product product)
	{
		Product s= prepo.save(product);
		return s;
	}
	
	public List<Product> saveallproduct(List<Product> product)
	{
		List<Product> prod=prepo.saveAll(product);
		return prod;
	}
	public List<Product> Getall()
	{
		List<Product> All = prepo.findAll();
		return All;
	}
	public Product getbyid(int id)
	{
		Optional<Product> prod = prepo.findById(id);
		return prod.get();
	}
	
	public String deleteprod(int id)
	{
		prepo.deleteById(id);
		return "Product deleted   "+id;
	}
	public void updateproduct(Product p)
	{
		
		Optional<Product> prod = prepo.findById(p.getId());
		System.out.println(prod);
				Product pid=prod.get();
				pid.setName(p.getName());
				pid.setPrice(p.getPrice());
				pid.setQuantity(p.getQuantity());
				System.out.println(pid);
				prepo.save(pid);
				
}
}