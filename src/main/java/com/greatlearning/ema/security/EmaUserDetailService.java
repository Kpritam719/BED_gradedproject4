package com.greatlearning.ema.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.ema.controller.UserRepository;
import com.greatlearning.ema.security.entity.User;

@Service
public class EmaUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		User entityObj =  userRepository.getUserByUsername(username);
		
		EmaUserDetails userDetails = new EmaUserDetails(entityObj);
		// TODO Auto-generated method stub
		return  userDetails;
	}
}
