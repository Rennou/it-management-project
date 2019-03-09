package com.bdeb.application.projectmanagement.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdeb.application.projectmanagement.forms.ResourceForm;
import com.bdeb.application.projectmanagement.model.Resource;
import com.bdeb.application.projectmanagement.repository.ResourceRepository;

@Service
public class ResourceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceService.class);

	@Autowired
	ResourceRepository resourceRepository;

	@Autowired
	UserService userService;

	public List<Resource> getResourceList() {
		LOGGER.info("getResourceList...");
		return resourceRepository.getList();
	}
	
	public List<Resource> getResourceList(String roleCode) {
		LOGGER.info("getResourceList...");
		return resourceRepository.getList(roleCode);
	}
	
	public List<Resource> getResourceList(List<String> roles) {
		LOGGER.info("getResourceList...");
		return resourceRepository.getList(roles);
	}

	public boolean addResource(ResourceForm resourceForm) {
		LOGGER.info("addResource...");
		Resource resource = resourceForm.getResource();
		resource.setRole(resourceForm.getRole());
		resource.setAccountCreated(false);
		resource.setUserIns(userService.getAuthentifiedUser());
		resource.setDateIns(new Date());

		resourceRepository.add(resource);

		resource.setAccountCreated(userService.addUserAccount(resourceForm));
		resourceRepository.update(resource);
		return true;
	}

}
