package com.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testcontroller {

	@GetMapping("/")
	@ResponseBody
	public String getmessage() {
		return "hello world";
	}

}
