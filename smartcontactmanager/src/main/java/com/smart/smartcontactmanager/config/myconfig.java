package com.smart.smartcontactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration 
@EnableWebSecurity
public class myconfig extends WebSecurityConfigurerAdapter {

/**
 * 1 :  create userdetailservice where we get username
 * 2 :  create passwordencoder where we get password
 * 3 :  create authenticationprovider and set userdetailservice and oasswordencoder
 * 4:  override configur method and set authentication provider
 * 5 :  override configur method and set authorised request by role
 */


    @Bean
    public UserDetailsService getuserdetailservice()
    {
        return new userdetailserviceimpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordencoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoauthenticationprovider=new DaoAuthenticationProvider();
        daoauthenticationprovider.setUserDetailsService(this.getuserdetailservice());
        daoauthenticationprovider.setPasswordEncoder(passwordencoder());

        return daoauthenticationprovider;
    }

    //config methods

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      
        //for database authentication
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// for admin routes
    	 // for user routes
    	//for all
    	 // for form login and csrf disable
    //set custom login page (url name)
    	
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN") 
        .antMatchers("/user/**").hasRole("USER")                         
        .antMatchers("/**").permitAll()                                     
        .and().formLogin().loginPage("/signin")
        .loginProcessingUrl("/dologin")
        .defaultSuccessUrl("/user/index")
//        .failureUrl("/login_fail")
        
        .and().csrf().disable();                        
    }

    


    
}
