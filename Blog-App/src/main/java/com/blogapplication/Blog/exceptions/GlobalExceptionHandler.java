package com.blogapplication.Blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.blogapplication.Blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourcenotfoundexception(ResourceNotFoundException ex){
		String message=ex.getMessage();
		
		return new ResponseEntity<>(new ApiResponse(message,false),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> HandleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		Map<String, String> errmsg=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String field = ((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errmsg.put(field, message);

		});
		return new ResponseEntity<>(errmsg,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse> httpmethodnotsupported(HttpRequestMethodNotSupportedException ex){
		
		return new ResponseEntity<>(new ApiResponse("Method not allowed !!",false),HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<ApiResponse> internalServerError(InternalServerError ex){
		
		return new ResponseEntity<>(new ApiResponse(ex.getMessage(),false),HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiResponse> notFoundServerError(NotFoundException ex){
		
		return new ResponseEntity<>(new ApiResponse(ex.getMessage(),false),HttpStatus.NOT_FOUND);		
	}
	
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> apiexception(ApiException ex){
		
		return new ResponseEntity<>(new ApiResponse(ex.getMessage(),false),HttpStatus.BAD_REQUEST);		
	}

}
