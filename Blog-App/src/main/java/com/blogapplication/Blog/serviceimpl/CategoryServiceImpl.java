package com.blogapplication.Blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapplication.Blog.exceptions.ResourceNotFoundException;
import com.blogapplication.Blog.models.Categories;
import com.blogapplication.Blog.payloads.CategoryDto;
import com.blogapplication.Blog.payloads.CategoryResponse;
import com.blogapplication.Blog.reprsitories.CategoryRepo;
import com.blogapplication.Blog.services.Categoryservice;

@Service
public class CategoryServiceImpl implements Categoryservice {

	private CategoryRepo categoryrepo;
	
	private ModelMapper modelmapper;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepo categoryrepo, ModelMapper modelmapper) {
		super();
		this.categoryrepo = categoryrepo;
		this.modelmapper = modelmapper;
	}

	@Override
	public CategoryDto createcategory(CategoryDto dto) {
		// TODO Auto-generated method stub
		Categories map = this.modelmapper.map(dto, Categories.class);
		Categories save = this.categoryrepo.save(map);
		CategoryDto map2 = this.modelmapper.map(save, CategoryDto.class);
		
		return map2;
	}

	@Override
	public CategoryDto updatecategory(CategoryDto dto, long id) {
		// TODO Auto-generated method stub
		Categories category = this.categoryrepo.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Category", "Category id" , id));
		if(dto.getCategorydescription()!=null) {
			category.setCategorydescription(dto.getCategorydescription());
		}
		if(dto.getCategotyTitle()!=null) {
			category.setCategotyTitle(dto.getCategotyTitle());
		}
		
		Categories save = this.categoryrepo.save(category);
		
		
		
		return this.modelmapper.map(save, CategoryDto.class);
	}

	@Override
	public void deletecategory(long id) {
		// TODO Auto-generated method stub
		Categories category = this.categoryrepo.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Category", "Category id" , id));
		this.categoryrepo.deleteById(category.getCategoryid());
		
	}

	@Override
	public CategoryDto getcategory(long id) {
		// TODO Auto-generated method stub
		Categories category = this.categoryrepo.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Category", "Category id" , id));
	
		return this.modelmapper.map(category, CategoryDto.class);
	}


	@Override
	public CategoryResponse getallcategory(int pagenumber,int pagesize,String sortby,String sortdir) {
		// TODO Auto-generated method stub
		
		Sort sort=(("asc").equalsIgnoreCase(sortdir)?Sort.by(sortby).ascending():Sort.by(sortby).descending());
		
		Pageable  pageble=PageRequest.of(pagenumber, pagesize,sort);
		Page<Categories> findAll = this.categoryrepo.findAll(pageble);
		List<CategoryDto> collect = findAll.getContent().stream().
		map(ctg->this.modelmapper.map(ctg, CategoryDto.class)).collect(Collectors.toList());
		
		System.out.println(collect);
		
		CategoryResponse response=new CategoryResponse();
		response.setContent(collect);
		response.setFirstpage(findAll.isFirst());
		response.setLastpage(findAll.isLast());
		response.setPagenumber(findAll.getNumber());
		response.setPagesize(findAll.getSize());
		response.setTotalelements(findAll.getTotalElements());
		response.setTotalpages(findAll.getTotalPages());
		
		return response;
	}

	
}
