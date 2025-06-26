package com.example.demo.web.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@RequiredArgsConstructor
public class SecurityConfig 
{
    private final DataSource dataSource;
    private final UserDetailsService customUserDetailsService;

    @SuppressWarnings("removal")
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception
    {
        // @formatter:off
    	http
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/login", "/webjars/**", "/css/**", "/js/**").permitAll() // This is correct for Spring Security 3.0
                .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
                .requestMatchers("/**").hasRole("USER")
                .anyRequest().authenticated()
        )
        .formLogin(formLogin ->
            formLogin
                .loginProcessingUrl("/login-check")
                .loginPage("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error")
                .permitAll()
        )
        .logout(logout ->
            logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
        )
        .rememberMe(rememberMe ->
            rememberMe
                .tokenRepository(persistentTokenRepository())
        );

    return http.build();
     // @formatter:on
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository()
    {
        JdbcTokenRepositoryImpl repositoryImpl = new JdbcTokenRepositoryImpl();
        repositoryImpl.setDataSource(dataSource);
        return repositoryImpl;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserDetailsService);
    }

}
