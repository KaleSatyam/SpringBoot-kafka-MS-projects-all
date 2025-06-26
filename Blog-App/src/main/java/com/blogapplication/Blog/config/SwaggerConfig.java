package com.blogapplication.Blog.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.service.contexts.SecurityContext;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	public static final String AUTHORIZATION_HEADER="authorization";
	
	private ApiKey apikey() {
		return new ApiKey("jwt", AUTHORIZATION_HEADER, "header");
	}
	
	private List<SecurityContext> securitycontext(){
		return Arrays.asList(SecurityContext.builder().securityReferences(securityreferences()).build());
				}
	
	private List<SecurityReference> securityreferences(){
		AuthorizationScope scope=new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("jwt", new AuthorizationScope[] {scope}));
	}
	
	@Bean
	public Docket api() {
		
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(securitycontext()).
				securitySchemes(Arrays.asList(apikey()))
				.select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
	     
	     ApiInfo apiInfo = new ApiInfo("Blog Application-Backend", "created by Satyam kale ..."
	     		+ "\n Some custom description of API.", "API TOS", "Terms of service",
	    		new springfox.documentation.service.Contact("Satyam", null, "satyamkalesk12345@gmail.com"), "License of API", "API license URL", Collections.emptyList());
	return apiInfo;
	}

}
