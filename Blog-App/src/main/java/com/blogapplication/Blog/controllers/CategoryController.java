package com.blogapplication.Blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapplication.Blog.payloads.ApiResponse;
import com.blogapplication.Blog.payloads.CategoryDto;
import com.blogapplication.Blog.payloads.CategoryResponse;
import com.blogapplication.Blog.services.Categoryservice;
import com.blogapplication.Blog.utils.Constants;

@RestControllerAdvice
@RequestMapping("api/category")
public class CategoryController {
	
	private Categoryservice cateservice;

	@Autowired
	public CategoryController(Categoryservice cateservice) {
		super();
		this.cateservice = cateservice;
	}
	
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createcategory(@Valid @RequestBody CategoryDto dto){
		CategoryDto createcategory = this.cateservice.createcategory(dto);
		
		return new ResponseEntity<>(createcategory,HttpStatus.CREATED);
	}
	//update
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto dto,@PathVariable("id") long cid){
		CategoryDto updatecategory = this.cateservice.updatecategory(dto, cid);
		return new ResponseEntity<>(updatecategory,HttpStatus.CREATED);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deletecategory(@PathVariable("id") long cid){
		this.cateservice.deletecategory(cid);
		
		return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getcategory(@PathVariable("id") long cid){
		CategoryDto getcategory = this.cateservice.getcategory(cid);
		return new ResponseEntity<>(getcategory,HttpStatus.CREATED);
	}
	
	//getall
	@GetMapping("")
	public ResponseEntity<CategoryResponse> getAllcategory(
			@RequestParam(name  = "pagenumber",defaultValue = Constants.pagenumber,required = false) int pagenumber,
			@RequestParam(name = "pagesize",defaultValue = Constants.pagesize,required = false) int pagesize,
			@RequestParam(value = "sortby", defaultValue = Constants.category_sortby,required = false) String sortby,
			@RequestParam(value = "sortdir", defaultValue = Constants.sort_direction,required = false) String sortdir
			){
		 CategoryResponse getallcategory = this.cateservice.getallcategory(pagenumber, pagesize, sortby, sortdir);
		 
		return new ResponseEntity<>(getallcategory,HttpStatus.CREATED);
	}
	
	


}
