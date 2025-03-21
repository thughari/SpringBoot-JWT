package com.thughari.jwtimplimentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thughari.jwtimplimentation.model.Users;
import com.thughari.jwtimplimentation.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return service.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		return service.verifyUser(user);
	}
	

}
