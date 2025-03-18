package com.thughari.jwtimplimentation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thughari.jwtimplimentation.model.UserDetals;
import com.thughari.jwtimplimentation.model.Users;
import com.thughari.jwtimplimentation.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("user cannot be found with the user name : " + username);
		}
		return new UserDetals(user);
	}

}
