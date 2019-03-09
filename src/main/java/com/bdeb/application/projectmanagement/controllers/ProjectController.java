package com.bdeb.application.projectmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdeb.application.projectmanagement.forms.ProjectForm;
import com.bdeb.application.projectmanagement.forms.ResourceForm;
import com.bdeb.application.projectmanagement.service.ProjectService;
import com.bdeb.application.projectmanagement.service.ResourceService;
import com.bdeb.application.projectmanagement.service.UserService;

@RequestMapping("/projects")
@Controller
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	ProjectService projectService;

	@Autowired
	UserService userService;

	@Autowired
	ResourceService resourceService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getProjectList(Model uiModel) {
		LOGGER.info("getProjectList()...");
		uiModel.addAttribute("projects", projectService.getUserProjects());
		if (userService.hasRole("ADMIN")) {
			return "admin/projects-list";
		} else {
			return "user/projects-list";
		}
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String newForm(@RequestParam(value = "form") String form, Model uiModel) {
		ProjectForm projectForm = new ProjectForm();

		projectForm.setManagers(resourceService.getResourceList("PM"));

		List<String> userRoles = new ArrayList<String>();
		userRoles.add("DEV");
		userRoles.add("QAA");
		userRoles.add("PO");
		projectForm.setResources(resourceService.getResourceList(userRoles));

		uiModel.addAttribute("projectForm", projectForm);
		return "admin/projects-edit";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String update(@Valid ProjectForm projectForm, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
		String message = null;

		if (bindingResult.hasErrors()) {
			message = "Erreur de validation des données: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
		}
		if (null != message) {
			uiModel.addAttribute("message", message);
			uiModel.addAttribute("projectForm", projectForm);
			return "admin/projects-edit";
		}

		projectService.addProject(projectForm);

		return "redirect:/projects/list";
	}

}
