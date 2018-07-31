package com.ar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ar.dto.CustomUserDetails;
import com.ar.model.User;
import com.ar.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> returnVal = userRepository.findByUsername(username);
		if (!returnVal.isPresent()) {
			throw new UsernameNotFoundException(
					new StringBuilder("UserName ").append(username).append(" not found").toString());
		}
		return new CustomUserDetails(returnVal.get());
	}
}
