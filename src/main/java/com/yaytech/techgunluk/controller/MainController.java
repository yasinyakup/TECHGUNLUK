package com.yaytech.techgunluk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yaytech.techgunluk.dto.RegisterDto;

@RestController
@RequestMapping("/api/auth/")
public class MainController {
	
	@GetMapping("/signup")
	public String signUp() {
		
		return("Welcome to signup page!!");
		
	}
	@GetMapping("/home")
	public String home() {
		return "Welcome to Home Page";
	}
}
