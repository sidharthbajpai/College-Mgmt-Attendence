package com.college.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.college.entity.User;
import com.college.service.UserService;

@Service
public class UserSecurityDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if(user==null)  {
			throw new UsernameNotFoundException("Userdetails not found for the user: " + username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		String authorityString = user.getRole().getRoleName().toString();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authorityString);
		authorities.add(authority);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}

}
