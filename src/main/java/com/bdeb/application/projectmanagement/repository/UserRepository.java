package com.bdeb.application.projectmanagement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.bdeb.service.user.Role;
import com.bdeb.service.user.User;

@Repository
public class UserRepository {

	@Autowired 
	RestTemplate resteTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User getUserByUsername(String username) {
		User user = new User();
		user.setUsername("tanis");
		user.setPassword(passwordEncoder.encode("123456"));
		user.setEmail("tanis.rennou@yahoo.fr");
		user.getRoles().add(Role.DEV);
		return user;
	}
}
