package com.blogapplication.Blog.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogapplication.Blog.security.JwtAuthenticationEntryPoint;
import com.blogapplication.Blog.security.JwtAuthenticationFilter;

import com.blogapplication.Blog.security.UserDetailService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class securityConfig   {

	public static final String[] PUBLIC_URL= {
			"/api/v1/auth/**",
			"/v3/api-docs",
			"/v2/api-docs","/swagger-resources/**","swagger-ui/**","/webjars/**"
			
	};
	
	@Autowired
	private UserDetailService customuserdetailservice;
	
	@Autowired
	private JwtAuthenticationEntryPoint JwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter JwtAuthenticationFilter;
	
//	@Bean
//	public SecurityFilterChain securityfilterchain(HttpSecurity http)throws Exception{
//		http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//		return http.build();
//	}
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http)throws Exception{
		http.csrf().disable().authorizeHttpRequests()
		.antMatchers(PUBLIC_URL).permitAll()
		.antMatchers(HttpMethod.GET).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(this.JwtAuthenticationEntryPoint)
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authenticationProvider(this.authenticationprovider());
		
		http.addFilterBefore(this.JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
//		httpBasic();
		return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationprovider() {
		DaoAuthenticationProvider provide=new DaoAuthenticationProvider();
		provide.setUserDetailsService(this.customuserdetailservice);
		provide.setPasswordEncoder(this.passwordencoder());
		
		return provide;
	}
	
	@Bean
	public AuthenticationManager authmanagerBean(AuthenticationConfiguration auth) throws Exception
	{ 
		return auth.getAuthenticationManager();
		
	}
	
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	

}
