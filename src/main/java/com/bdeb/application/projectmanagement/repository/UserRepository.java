package com.bdeb.application.projectmanagement.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.bdeb.service.commun.SecurityHeader;
import com.bdeb.service.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class UserRepository {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ObjectMapper jsonMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${service.user.url}")
	String baseUrl;

	public User getUser(SecurityHeader header, String username) {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			List<MediaType> accepts = new ArrayList<MediaType>();
			accepts.add(MediaType.APPLICATION_JSON);
			headers.setAccept(accepts);

			headers.add("Security-Header", jsonMapper.writeValueAsString(header));

			HttpEntity<?> request = new HttpEntity<>(headers);

			ResponseEntity<User> response = restTemplate.exchange(baseUrl + "/" + username, HttpMethod.GET, request,
					User.class);

			return response.getBody();

		} catch (IOException e) {

			LOGGER.error("Error when trying to call user service: {}", e.getMessage());

		} catch (HttpClientErrorException | HttpServerErrorException e) {

			LOGGER.error("Error when trying to call user service: {}", e.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean addUser(SecurityHeader header, User user) {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Security-Header", jsonMapper.writeValueAsString(header));

			HttpEntity<User> request = new HttpEntity<>(user, headers);

			restTemplate.exchange(baseUrl + "/add", HttpMethod.POST, request, User.class);

			return true;

		} catch (IOException e) {

			LOGGER.error("Error when trying to call user service: {}", e.getMessage());

		} catch (HttpClientErrorException | HttpServerErrorException e) {

			LOGGER.error("Error when trying to call user service: {}", e.getResponseBodyAsString());

		} catch (Exception e) {

			LOGGER.error("Error when trying to call user service: {}", e.getMessage());

		}

		return false;

	}
}
