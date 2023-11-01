package com.techbuild.techbuild.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		logger.info("Entering securityFilterChain()");
		http.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
		return http.build();
	}
}
