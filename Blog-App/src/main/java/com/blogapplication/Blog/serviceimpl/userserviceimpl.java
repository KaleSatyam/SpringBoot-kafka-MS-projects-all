package com.blogapplication.Blog.serviceimpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapplication.Blog.exceptions.ResourceNotFoundException;
import com.blogapplication.Blog.models.Role;
import com.blogapplication.Blog.models.User;
import com.blogapplication.Blog.payloads.UserResponse;
import com.blogapplication.Blog.payloads.Userdto;
import com.blogapplication.Blog.reprsitories.Rolerepo;
import com.blogapplication.Blog.reprsitories.userrepo;
import com.blogapplication.Blog.services.userservice;
import com.blogapplication.Blog.utils.Constants;

@Service
public class userserviceimpl implements userservice {
	@Autowired
	private userrepo userrepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private Rolerepo rolerepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public Userdto createuser(Userdto user) {
		// TODO Auto-generated method stub
		
		User save = userrepo.save(dtotouser(user));
		Userdto usertoDto = usertoDto(save);
		return usertoDto;
	}

	@Override
	public Userdto updateeuser(Userdto userdto, Long id) {
		// TODO Auto-generated method stub
		User user1 = userrepo.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Userid", "Id", id));
		
		if(userdto.getUsername()!=null)
		{
			user1.setUsername(userdto.getUsername());
		}
		if(userdto.getAddress()!=null)
		{
			user1.setAddress(userdto.getAddress());
		}
		if(userdto.getEmail()!=null)
		{
			user1.setEmail(userdto.getEmail());;
		}
		if(userdto.getPassword()!=null)
		{
			user1.setPassword(userdto.getPassword());
		}
		if(userdto.getName()!=null)
		{
			user1.setName(userdto.getName());
		}
		
		User save = userrepo.save(user1);
		Userdto usertoDto = this.usertoDto(save);		
		return usertoDto;
	}

	@Override
	public UserResponse getalluser(int pagenumber,int pagesize,String sortby,String sortdir) {
		
		// TODO Auto-generated method stub
		Sort sort=("asc").equalsIgnoreCase(sortdir)?Sort.by(sortby).ascending():Sort.by(sortby).descending();
		Pageable pageable=PageRequest.of(pagenumber, pagesize, sort);
		Page<User> findAll = this.userrepo.findAll(pageable);
		List<Userdto> collect = findAll.getContent().stream().map(user->this.modelmapper.map(user, Userdto.class) ).collect(Collectors.toList());
		
		UserResponse response=new UserResponse();
		response.setContent(collect);
		response.setFirstpage(findAll.isFirst());
		response.setLastpage(findAll.isLast());
		response.setPagenumber(findAll.getNumber());
		response.setPagesize(findAll.getSize());
		response.setTotalelements(findAll.getTotalElements());
		response.setTotalpages(findAll.getTotalPages());
		
		return response;
	}

	@Override
	public Userdto getuserbyid(Long id) {
		// TODO Auto-generated method stub
		User user1 = userrepo.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Userid", "Id", id));
		Userdto usertoDto = this.usertoDto(user1);
		return usertoDto;
	}

	@Override
	public void Deleteuser(Long id) {
		// TODO Auto-generated method stub
		User user1 = userrepo.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Userid", "Id", id));
		
		 this.userrepo.deleteById(user1.getUserid());
	}
	
	
	public User dtotouser(Userdto userdto){
//	User userd=new User();
//	userd.setUserid(userdto.getUserid());
//	userd.setAddress(userdto.getAddress());
//	userd.setEmail(userdto.getEmail());
//	userd.setName(userdto.getName());
//	userd.setUsername(userdto.getUsername());
//	userd.setPassword(userdto.getPassword());
		User map = this.modelmapper.map(userdto, User.class);
	
	return map;
	}
	
	public Userdto usertoDto(User user) {
//		Userdto dto=new Userdto();
//		dto.setAddress(user.getAddress());
//		dto.setName(user.getName());
//		dto.setEmail(user.getEmail());
//		dto.setPassword(user.getPassword());
//		dto.setUserid(user.getUserid());
//		dto.setUsername(user.getUsername());
		
		Userdto map = this.modelmapper.map(user, Userdto.class);
		return map;
		
	}

	@Override
	public Userdto registerUser(Userdto userdto) {
		// TODO Auto-generated method stub
		User map = this.modelmapper.map(userdto, User.class);
		map.setPassword(this.encoder.encode(map.getPassword()));
		Role role = this.rolerepo.findById(Constants.normal_user).get();
		map.getRole().add(role);
		
		User save = this.userrepo.save(map);
		
		return this.modelmapper.map(save, Userdto.class );
	}

}
