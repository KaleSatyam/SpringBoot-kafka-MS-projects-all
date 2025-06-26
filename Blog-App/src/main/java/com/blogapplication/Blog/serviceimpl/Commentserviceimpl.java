package com.blogapplication.Blog.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.Blog.exceptions.ResourceNotFoundException;
import com.blogapplication.Blog.models.Comment;
import com.blogapplication.Blog.models.Post;
import com.blogapplication.Blog.models.User;
import com.blogapplication.Blog.payloads.CommentDto;
import com.blogapplication.Blog.reprsitories.Commentrepor;
import com.blogapplication.Blog.reprsitories.Postrepository;
import com.blogapplication.Blog.reprsitories.userrepo;
import com.blogapplication.Blog.services.commentservice;

@Service
public class Commentserviceimpl implements commentservice{
	
	private Postrepository postrepo;
	
	private userrepo userrepo;
	
	private Commentrepor commentrepo;
	
	private ModelMapper mapper;
	
	@Autowired
	public Commentserviceimpl(Postrepository postrepo, com.blogapplication.Blog.reprsitories.userrepo userrepo,
			Commentrepor commentrepo, ModelMapper mapper) {
		super();
		this.postrepo = postrepo;
		this.userrepo = userrepo;
		this.commentrepo = commentrepo;
		this.mapper = mapper;
	}
	
	@Override
	public CommentDto createcomment(CommentDto dto, Long postid,Long userid) {
		User user = this.userrepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User", "userid", userid));
		Post post = this.postrepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post", "postid", postid));
		Comment comm=  this.mapper.map(dto, Comment.class);
		comm.setPost(post);
		comm.setUser(user);
		Comment save = this.commentrepo.save(comm);
		return this.mapper.map(save, CommentDto.class);
	}

	@Override
	public void deletecomment(Long id) {
		// TODO Auto-generated method stub
		Comment comment = this.commentrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment", "Commentid", id));
		this.commentrepo.delete(comment);
	}

}
