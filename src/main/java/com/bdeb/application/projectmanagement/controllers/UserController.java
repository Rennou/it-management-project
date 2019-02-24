package com.bdeb.application.projectmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bdeb.application.projectmanagement.security.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/home")
	public String home(Model model) {
		//System.out.println("User" +authenticatedUser.getUser().getUsername());
		if (userService.hasRole("ADMIN")) {
			return "redirect:admin";
		} else {
			List<String> userRoles = new ArrayList<String>();
			userRoles.add("PM");
			userRoles.add("DEV");
			userRoles.add("QAA");
			userRoles.add("PO");

			if (userService.hasAnyRole(userRoles)) {
				return "redirect:user";

			}
		}
		return null;
	}
	
	@GetMapping("/admin")
	public String adminHome(Model model) {
		return "admin/home";
	}

	@GetMapping("/user")
	public String userHome(Model model) {
		return "user/home";
	}

}
