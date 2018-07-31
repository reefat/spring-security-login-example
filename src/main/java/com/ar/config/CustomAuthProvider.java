package com.ar.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

	static Logger log = LogManager.getLogger(CustomAuthProvider.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		// TODO Auto-generated method stub

		String username = auth.getName();
		String password = auth.getCredentials().toString().trim();
		
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			log.error("Username or password can't be empty");
			throw new BadCredentialsException("Username or password can't be empty");
		}
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

		if (userDetails == null) {
			throw new BadCredentialsException("Invalid Credentials!");
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid username or password!");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
