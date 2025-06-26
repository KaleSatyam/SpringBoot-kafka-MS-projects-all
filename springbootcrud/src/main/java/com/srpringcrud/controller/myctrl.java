package com.srpringcrud.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.srpringcrud.entity.Product;
import com.srpringcrud.repository.productrepository;
import com.srpringcrud.service.productservice;

@Controller
public class myctrl {
	@Autowired
	productservice service;
	@Autowired
	productrepository repo;
	
	
	@RequestMapping(path = "/home")
	public String paddproduct()  
	{
		
		return "new";
	}
	
	@RequestMapping(path="/view",method=RequestMethod.POST)
	public RedirectView Viewall(@ModelAttribute Product product,Model model)
	{
		RedirectView  view=new RedirectView();
		 Product saveproduct = service.saveproduct(product);
		 model.addAttribute("save",saveproduct);
		 System.out.println(saveproduct);
		 
			 view.setUrl("home");
			return view;
		
	}
	@RequestMapping("/viewprod")
	public String getallproduct(Model model)
	{
		List<Product> getall = this.service.Getall();
		model.addAttribute("getall",getall);
		return "viewprod";
	}
	
	
	@RequestMapping("/delete/{id}")
	public String deletebyid(@PathVariable("id") int id)
	{
		
		service.deleteprod(id);
		
		return "redirect:/viewprod";
		
	}
	@RequestMapping("/update/{id}")
	public String editproduct(@PathVariable("id") int id,Model model)
	{
	Product getbyid = service.getbyid(id);
		model.addAttribute("product",getbyid);
		return "update";
		
	}
	@RequestMapping(path="/submit", method=RequestMethod.POST)
	public String submiteditedproduct(@RequestParam("Id") int id
			,@RequestParam("Name") String name
			,@RequestParam("Quantity") int quantity
			,@RequestParam("Price") double price
			,Model  model)
	{		
	System.out.println(id+"  "+name+"  "+quantity+"  "+price);
		Product p=new Product();
		p.setId(id);
		p.setName(name);
		p.setQuantity(quantity);
		p.setPrice(price);
	service.updateproduct(p);
		return "redirect:/viewprod";
	}

}
