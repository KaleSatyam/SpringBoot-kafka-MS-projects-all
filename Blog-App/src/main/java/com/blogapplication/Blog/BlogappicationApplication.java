package com.blogapplication.Blog;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogapplication.Blog.config.securityConfig;
import com.blogapplication.Blog.models.Role;
import com.blogapplication.Blog.reprsitories.Rolerepo;
import com.blogapplication.Blog.utils.Constants;

@SpringBootApplication
public class BlogappicationApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private Rolerepo repor;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogappicationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
			Role role=new Role();
			role.setId(Constants.admin_user);
			role.setName("ROLE_ADMIN");
			
			Role role2=new Role();
			role2.setId(Constants.normal_user);
			role2.setName("ROLE_NORMAL");
			
			Set<Role> roleset = Set.of(role,role2);
			
			List<Role> saveAll = this.repor.saveAll(roleset);
			System.out.println(saveAll);
			
	
	}
	
	

}
