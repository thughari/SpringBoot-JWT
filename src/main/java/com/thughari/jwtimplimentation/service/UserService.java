package com.thughari.jwtimplimentation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.thughari.jwtimplimentation.model.Users;
import com.thughari.jwtimplimentation.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	public Users register(Users user) {
		return repo.save(user);
	}

	public String verifyUser(Users user) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		return authentication.isAuthenticated()?jwtService.generateToken(user.getUsername()):"fail";
	}
	
}
