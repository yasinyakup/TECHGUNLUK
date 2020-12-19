package com.yaytech.techgunluk.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yaytech.techgunluk.dto.RegisterDto;
import com.yaytech.techgunluk.dto.RegisterRequest;
import com.yaytech.techgunluk.service.AuthService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class MainController {
	public final AuthService authService;

	@GetMapping("/test")
	public String test() {

		return ("Welcome to test page!!");


	}
	
	@PostMapping("/signup")
	public ResponseEntity signUp(
			@RequestBody RegisterRequest registerRequest) {
		System.out.println("test");
		authService.signUp(registerRequest);
		return new ResponseEntity(HttpStatus.OK);

	}
	
	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> getMethodName(@PathVariable String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
	}
	
}
