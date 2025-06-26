package com.blogapplication.Blog.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
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
import com.blogapplication.Blog.models.Post;
import com.blogapplication.Blog.models.User;
import com.blogapplication.Blog.payloads.PostDto;
import com.blogapplication.Blog.payloads.PostResponse;
import com.blogapplication.Blog.reprsitories.CategoryRepo;
import com.blogapplication.Blog.reprsitories.Postrepository;
import com.blogapplication.Blog.reprsitories.userrepo;
import com.blogapplication.Blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {


	private Postrepository postrepor;
	private ModelMapper modelmapper;
	private userrepo userrepo;
	private CategoryRepo caterepo;
	
	@Autowired
	public PostServiceImpl(Postrepository postrepor, ModelMapper modelmapper,
			com.blogapplication.Blog.reprsitories.userrepo userrepo, CategoryRepo caterepo) {
		super();
		this.postrepor = postrepor;
		this.modelmapper = modelmapper;
		this.userrepo = userrepo;
		this.caterepo = caterepo;
	}

	public PostDto createpost(PostDto dto, Long userid, Long categoryid){
		// TODO Auto-generated method stub
		User user = this.userrepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("user", "Userid", userid));
		Categories category = this.caterepo.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category", "Categoryid", categoryid));
		
		Post map = this.modelmapper.map(dto, Post.class);
		map.setImage("default.png");
		map.setAdddateDate(new Date());
		map.setUser(user);
		map.setCategories(category);
		
		Post save = this.postrepor.save(map);
		
		return this.modelmapper.map(save, PostDto.class);
	}
	

	@Override
	public PostDto updatepost(PostDto dto, Long id) {
		// TODO Auto-generated method stub
		
		Post post = this.postrepor.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "Postid", id));
		
		if(dto.getTitle()!=null) {
			post.setTitle(dto.getTitle());
		}
		if(dto.getContent()!=null) {
			post.setContent(dto.getContent());
		}
		if(dto.getImage()!=null){
			post.setImage(dto.getImage());
		}
		
		Post save = this.postrepor.save(post);
		
		return this.modelmapper.map(save, PostDto.class);
	}

	@Override
	public void deletepost(Long id) {
		// TODO Auto-generated method stub
		
		Post post = this.postrepor.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "Postid", id));
		
		this.postrepor.delete(post);
		
	}

	@Override
	public PostDto getpost(Long id) {
		// TODO Auto-generated method stub
		Post post = this.postrepor.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "Postid", id));
		return this.modelmapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getallpost(int pagenumber,int pagesize,String sortby,String sortdir) {
		// TODO Auto-generated method stub
//		int pagesize=5;
//		int pagenumber=1;
		Sort sort= (("asc").equalsIgnoreCase(sortdir))?Sort.by(sortby).ascending():Sort.by(sortby).descending();
		
		Pageable p=PageRequest.of(pagenumber, pagesize, sort);
		Page<Post> findAll = this.postrepor.findAll(p);
		List<PostDto> content = findAll.getContent().stream().map(post->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
//		List<Post> allpost = this.postrepor.findAll(null);
//		List<PostDto> collect = content.stream().map(post->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(content);
		postResponse.setPagenumber(findAll.getNumber());
		postResponse.setPagesize(findAll.getSize());
		postResponse.setTotalelements(findAll.getTotalElements());
		postResponse.setTotalpages(findAll.getTotalPages());
		postResponse.setLastpage(findAll.isLast());
		postResponse.setFirstpage(findAll.isFirst());
		
		
		return postResponse;
	}

	@Override
	public List<PostDto> getpostbycategory(Long categoryid) {
		// TODO Auto-generated method stub
		Categories categ = this.caterepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", "Categoryid", categoryid));
		List<Post> posts = this.postrepor.findAllByCategories(categ);
		List<PostDto> collect = posts.stream().map(post->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public List<PostDto> getpostbyuser(Long userid) {
		// TODO Auto-generated method stub
		User user = this.userrepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userid));
		List<Post> posts = this.postrepor.findAllByUser(user);
		List<PostDto> post = posts.stream().map(p->this.modelmapper.map(p, PostDto.class)).collect(Collectors.toList());
		
		return post;
	}

	@Override
	public List<PostDto> searchpost(String keyword) {
		// TODO Auto-generated method stub
		List<Post> findByTitleContaining = this.postrepor.findByTitleContaining(keyword);
		List<PostDto> collect = findByTitleContaining.stream().map((p)->this.modelmapper.map(p, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	
	

}
