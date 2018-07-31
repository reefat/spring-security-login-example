package com.ar.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ar.model.User;
import com.ar.model.UserRole;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7915814045535986193L;

	private Collection<? extends GrantedAuthority> authorities;
	private String password;
	private String username;

	public CustomUserDetails(User user) {
		this.authorities = getAuthoritiesFromRoles(user.getRoles());
		this.password = user.getPassword();
		this.username = user.getUsername();
	}

	private Collection<? extends GrantedAuthority> getAuthoritiesFromRoles(List<UserRole> roles) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (UserRole role : roles) {
			String name = role.getName().toUpperCase();
			if (!name.startsWith("ROLE_")) {
				name = "ROLE_" + name;
			}
			authorities.add(new SimpleGrantedAuthority(name));
		}
		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
