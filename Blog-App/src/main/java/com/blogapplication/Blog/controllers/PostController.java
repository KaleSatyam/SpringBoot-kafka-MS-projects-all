package com.blogapplication.Blog.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapplication.Blog.payloads.ApiResponse;
import com.blogapplication.Blog.payloads.Fileresponse;
import com.blogapplication.Blog.payloads.PostDto;
import com.blogapplication.Blog.payloads.PostResponse;
import com.blogapplication.Blog.payloads.Userdto;
import com.blogapplication.Blog.services.FileService;
import com.blogapplication.Blog.services.PostService;
import com.blogapplication.Blog.utils.Constants;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	Logger logger=org.slf4j.LoggerFactory.getLogger(PostController.class);
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private FileService fileservice;
	
	@Autowired
	private PostService service;
	
	//create post
	@PostMapping("/user/{userid}/category/{catid}/posts")
	public ResponseEntity<PostDto> createpost(@Valid @RequestBody PostDto dto,@PathVariable Long userid,
			@PathVariable Long catid,Principal principal){
		System.out.println("name  "+principal.getName());
		PostDto createpost = this.service.createpost(dto, userid, catid);
		return new ResponseEntity<>(createpost,HttpStatus.CREATED);
	}
	
	//getbuuser
	@GetMapping("/user/{userid}/post")
	public ResponseEntity<List<PostDto>> getpostbyuser(@PathVariable Long userid){
		List<PostDto> getpostbyuser = this.service.getpostbyuser(userid);
		
		return new ResponseEntity<>(getpostbyuser,HttpStatus.OK);
	}
	
	//gebycategory
		@GetMapping("/category/{catid}/post")
		public ResponseEntity<List<PostDto>> getpostbycategory(@PathVariable Long catid){
			List<PostDto> getpostbycat = this.service.getpostbycategory(catid);
			
			return new ResponseEntity<>(getpostbycat,HttpStatus.OK);
		}
		
		//getonepost
		@GetMapping("/post/{postid}")
		public ResponseEntity<PostDto> getpostbyid(@PathVariable Long postid){
			PostDto getpost = this.service.getpost(postid);
			return new ResponseEntity<>(getpost,HttpStatus.OK);
		} 
		
		//getallpost
		@GetMapping("/post")
		public ResponseEntity<PostResponse> getallpost(
				@RequestParam(name  = "pagenumber",defaultValue = Constants.pagenumber,required = false) int pagenumber,
				@RequestParam(name = "pagesize",defaultValue = Constants.pagesize,required = false) int pagesize,
				@RequestParam(value = "sortby", defaultValue = Constants.post_sortby,required = false) String sortby,
				@RequestParam(value = "sortdir", defaultValue = Constants.sort_direction,required = false) String sortdir
				){
			PostResponse getpost = this.service.getallpost(pagenumber,pagesize,sortby,sortdir);
			return new ResponseEntity<>(getpost,HttpStatus.OK);
		} 
		
		//delete
		@DeleteMapping("/post/{postid}")
		public ResponseEntity<ApiResponse> deletepost(@PathVariable Long postid){
			this.service.deletepost(postid);
			
			return new ResponseEntity<>(new ApiResponse("Post deleted successfully !!", true) , HttpStatus.OK);
		}
		
		//update
		@PutMapping("/post/{postid}")
		public ResponseEntity<PostDto> upatepost(@RequestBody PostDto post,@PathVariable long postid){
//			logger.info("postdto {}",post);
//			Userdto user = post.getUser();
//			logger.info(user.toString());
			
			
			PostDto updatepost = this.service.updatepost(post, postid);
			
			return new ResponseEntity<>(updatepost,HttpStatus.OK);
		}
		
		//searchpost
		@GetMapping("/post/search/{keyword}")
		public ResponseEntity<List<PostDto>> searchpost(@PathVariable String keyword){
			List<PostDto> searchpost = this.service.searchpost(keyword);
			return new ResponseEntity<>(searchpost,HttpStatus.OK);
		}
		
		//upload image
		@PostMapping("/post/image/upload/{postid}")
		public ResponseEntity<PostDto> uploadImage(
				@PathVariable Long postid,
				@RequestParam("image") MultipartFile file) throws IOException{
			PostDto postdto = this.service.getpost(postid);
			String uploanImage = this.fileservice.uploanImage(path, file);
			
			postdto.setImage(uploanImage);
			PostDto updatepost = this.service.updatepost(postdto, postid);
			return new ResponseEntity<>(updatepost,HttpStatus.OK);
		}
		
		//serve image
		@GetMapping(value = "/post/image/{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
		public void serveimage(@PathVariable String imagename,
				HttpServletResponse response) throws IOException,FileNotFoundException {
			InputStream resource = this.fileservice.getResource(path, imagename);
			
			StreamUtils.copy(resource, response.getOutputStream());
			
		}
	

}
