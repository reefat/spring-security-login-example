package com.ar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthProvider customAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/resources/**", "/registration", "/public/**").permitAll()
				.antMatchers("/css/**").permitAll().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
				.permitAll().anyRequest().fullyAuthenticated().and()
				.formLogin().loginPage("/login")
				.failureUrl("/login?error").permitAll().and().logout()
				.logoutUrl("/app-logout")
				.logoutSuccessUrl("/login")
				.permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(customAuthProvider);
	}

}
