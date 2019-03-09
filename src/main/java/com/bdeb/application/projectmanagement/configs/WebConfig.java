package com.bdeb.application.projectmanagement.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bdeb.application.projectmanagement.util.DateFormatter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.bdeb.application.projectmanagement" })
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public Validator validator() {
		final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		return validator;
	}

	@Override
	public Validator getValidator() {
		return validator();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	   registry.addViewController("/").setViewName("login");
       registry.addViewController("/login").setViewName("login");
	}
	
	@Override
	public void addFormatters(FormatterRegistry formatterRegistry) {
		formatterRegistry.addFormatter(dateFormatter());
	}

	@Bean
	public DateFormatter dateFormatter() {
		return new DateFormatter();
	}
	
}