package com.bdeb.application.projectmanagement.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestServiceConfig {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
