package com.MedNex.healthcare.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// For demo: hardcoded user. Replace with DB lookup.
		if (username.equals("admin")) {
			return User.withUsername("admin").password("$2a$10$7QhZkQWzYQhZkQWzYQhZkQWzYQhZkQWzYQhZkQWzYQhZkQWzYQ") // bcrypt
					.authorities("ADMIN").build();
		}
		throw new UsernameNotFoundException("User not found");
	}
}
