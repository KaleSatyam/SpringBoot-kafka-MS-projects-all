package com.smart.smartcontactmanager.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.smartcontactmanager.entities.Contact;
import com.smart.smartcontactmanager.entities.User;
import com.smart.smartcontactmanager.helper.message;
import com.smart.smartcontactmanager.repository.Contactrepository;
import com.smart.smartcontactmanager.repository.Userrepository;

@Controller
@RequestMapping("/user")
public class usercontroller {
	
	@Autowired
	private Userrepository userrepo;
	
	@Autowired
	private Contactrepository contactrepor;

	@ModelAttribute
	public void getcommondata(Model model,Principal principal){

		String name = principal.getName();
			
			System.out.println("username" +name);
			User userByUserName = this.userrepo.getUserByUserName(name);
			System.out.println(userByUserName);
			model.addAttribute("loggeduser",userByUserName);
		

	}
	
	//home dashboard
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal)
	{
	
		return "normaluser/dashboard";
	}

	//open add form contriller
	@GetMapping("/add-contact")
	public String openaddcontactform(Model model)
	{
		model.addAttribute("title", "add-contact");
		model.addAttribute("contact", new Contact());
		return "normaluser/add_contact_form";
	}
	
	//processing add contact form
	@PostMapping("/process_contact")
	public String procescontact(@ModelAttribute Contact contactobj,
			@RequestParam("ProfileImage") MultipartFile profileimg ,
			Principal principle,HttpSession session)
	{
		try {
		String name = principle.getName();
		User user = this.userrepo.getUserByUserName(name);
		
		
		//imageprocessing
		if(profileimg.isEmpty())
		{
			System.out.println("profile image is empty");
			
		}
		else {
			//uploading code
			int id = user.getId();
			String originalFilename = profileimg.getOriginalFilename();
			String filename=id+originalFilename;
			
			System.out.println(filename);
			contactobj.setImage(filename);
			
			
//			getpath for image save
			File savefile = new ClassPathResource("static/images").getFile();
			
			Path path = Paths.get(savefile.getAbsolutePath()+File.separator+filename);
			
			Files.copy(profileimg.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("image path : "+path);
			System.out.println("image uploaded");
		}
		
		
		contactobj.setUser(user);
		
		user.getContact().add(contactobj);
		this.userrepo.save(user);
		System.out.println("data  :"+contactobj);
		System.out.println("Data saved to database");
		
		//message success ....
		session.setAttribute("message", new message("Your contact added  !!  add more ... ", "alert-success"));
		
		
		}catch(Exception e){
			System.out.println("error :"+ e);
			e.printStackTrace();
			
			//error message ....
			session.setAttribute("message", new message("Something went wront try again ! ! ", "alert-danger"));
			
		}
		
		return "normaluser/add_contact_form";
	}
	
	@GetMapping("/showcontacts/{page}/{size}")
	public String showcontacts(@PathVariable int page,@PathVariable int size,Model m,Principal p) {
		m.addAttribute("title","Show user contacts");
		String name = p.getName();
		User user = this.userrepo.getUserByUserName(name);
		
		Pageable pageble=PageRequest.of(page, size);
		
	  Page<Contact> contacts = this.contactrepor.findAllByUser(user,pageble);
		
//		System.out.println(contacts);
		m.addAttribute("contacts",contacts);
		m.addAttribute("currentpage",page);
		m.addAttribute("totalpages",contacts.getTotalPages());
		
		return "normaluser/show_contacts";
	}
	
}
