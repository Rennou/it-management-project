package com.bdeb.application.projectmanagement.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bdeb.application.projectmanagement.service.ProjectService;
import com.bdeb.application.projectmanagement.service.UserService;

@RequestMapping("/projects")
@Controller
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getProjectList(Model uiModel) {
		LOGGER.info("getProjectList()...");
		uiModel.addAttribute("resources", projectService.getUserProjects());
		if(userService.hasRole("ADMIN")) {
		return "admin/projects-list";
		} else {
			return "user/projects-list";
		}
	}
}
