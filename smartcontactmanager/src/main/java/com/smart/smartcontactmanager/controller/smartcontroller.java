package com.smart.smartcontactmanager.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.smart.smartcontactmanager.helper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.smartcontactmanager.entities.User;
import com.smart.smartcontactmanager.repository.Userrepository;
import com.smart.smartcontactmanager.service.smartcontactService;

@Controller
public class smartcontroller {
		
	@Autowired
 	private	smartcontactService contactservice;

	 @Autowired
	 private BCryptPasswordEncoder passwordencoder;
	
	//home request handler
		@GetMapping("/")
		public String home(Model model)
		{

			return "home";
		}
		
		//about request handler
		@GetMapping("/about")
		public String about(Model model)
		{

			return "about";
		}
		
		//signup request handler
		@GetMapping("/signup")
		public String signup(Model model)
		{
			model.addAttribute("title"," smart contact manager project");
//			when u fire sign up its sending blank data by model
			model.addAttribute("user", new User());
			return "signup";
		}
		
		
//       for registering user
		// @Transactional(rollbackOn = Exception.class)
		@PostMapping("/register")
		public String registeruser(@Valid @ModelAttribute User user,BindingResult result,
									@RequestParam(value = "agreement",defaultValue = "false") 
									boolean agreement,Model model, HttpSession session)
		{
			try {
				
				if(!agreement)
				{
					System.out.println("You have not agreed terms and conditions");
					throw new Exception("You have not agreed terms and conditions");
				}

				if(result.hasErrors())
				{
					System.out.println("error is   "+result.toString());
					model.addAttribute("user", user);
					
					return "signup";
				}
				user.setRole("ROLE_USER");
				user.setEnabled(true);
				user.setImageurl("default.jpg");
				user.setPassword(passwordencoder.encode(user.getPassword()));
				
				System.out.println("encoded password  " + user.getPassword());
				System.out.println("agreement   "+agreement);
				System.out.println("User  "+user);
				
				User save = this.contactservice.saveuser(user);
					
				
				model.addAttribute("user",new User());
				session.setAttribute("message",new message("Successfully Registered", "alert-success"));
				return "signup";
		
			} catch (Exception e) {
				
				e.printStackTrace();
				model.addAttribute("user",user);
				session.setAttribute("message",new message("Something went wrong try again !!!"+e.getMessage(), "alert-danger"));
				return "signup";
			}
		}
		
		//login	
		@GetMapping("/signin")
		public String customlogin(Model model)
		{
			model.addAttribute("title"," logged in page");
			System.out.println("in signin");
			return	"Login";
		}
	
}
