package com.techbuild.techbuild.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.domain.User;
import com.techbuild.techbuild.service.UserService;

import jakarta.servlet.http.HttpSession;

@Configuration
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Entering loadUserByUsername()");
		logger.info("Username {}", username);

		User user = userService.getUserByEmail(username);

		if (user != null) {
			logger.info("User ID: {}", user.getId());
			session.setAttribute("user_id", user.getId());
			session.setAttribute("username", user.getName());
			return org.springframework.security.core.userdetails.User.builder()
					.username(user.getName())
					.password(encoder().encode(user.getPassword()))
					.roles(user.getRole()).build();
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}
}
