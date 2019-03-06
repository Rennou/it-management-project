package com.bdeb.application.projectmanagement.controllers;

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

import com.bdeb.application.projectmanagement.forms.ResourceForm;
import com.bdeb.application.projectmanagement.service.ResourceService;

@RequestMapping("/resources")
@Controller
public class ResourceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	ResourceService resourceService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getResourceList(Model uiModel) {
		LOGGER.info("getResourceList()...");
		uiModel.addAttribute("resources", resourceService.getResourceList());
		return "admin/resources-list";
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String newForm(@RequestParam(value = "form") String form, Model uiModel) {
		uiModel.addAttribute("resourceForm", new ResourceForm());
		return "admin/resources-edit";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String update(@Valid ResourceForm resourceForm, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
		String message = null;
		if (bindingResult.hasErrors()) {
			message = "Erreur de validation des données: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
		} else {
			message = resourceForm.valider();
		}
		if (null != message) {
			uiModel.addAttribute("message", message);
			uiModel.addAttribute("resourceForm", resourceForm);
			return "admin/resources-edit";
		}
		
		resourceService.addResource(resourceForm);

		return "redirect:/resources/list";
	}
}
