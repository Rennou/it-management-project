package com.bdeb.application.projectmanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdeb.application.projectmanagement.forms.ProjectForm;
import com.bdeb.application.projectmanagement.model.Project;
import com.bdeb.application.projectmanagement.model.ProjectResource;
import com.bdeb.application.projectmanagement.repository.ProjectRepository;
import com.bdeb.application.projectmanagement.repository.ResourceRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ResourceRepository resourceRepository;
	
	
	public List <Project> getUserProjects(){
		List<Project> list = new ArrayList<Project>();
		if(userService.hasRole("ADMIN")) {
			list = projectRepository.getList();
		}else {
			if(userService.hasRole("PM")) {
				list = projectRepository.getListByManger(userService.getAuthentifiedUser());
			}else {
				list = projectRepository.getList(userService.getAuthentifiedUser());
			}
		}
		return list;
	}
	
	public boolean addProject(ProjectForm projectForm) {
		
		Project project = projectForm.getProject();
		String userIns =userService.getAuthentifiedUser();
		project.setUserIns(userIns);
		Date now = new Date();
		project.setDateIns(now);
		if(null != projectForm.getManagerId() && 0 < projectForm.getManagerId().trim().length()) {
			project.setResource(resourceRepository.getResource(projectForm.getManagerId().trim()));
		}
		
		if(null != projectForm.getRessourceIds() && 0 < projectForm.getRessourceIds().size()) {
			for(String id : projectForm.getRessourceIds()) {
				ProjectResource pResource = new ProjectResource();
				pResource.setDateIns(now);
				pResource.setUserIns(userIns);
				pResource.setResource(resourceRepository.getResource(id));
				pResource.setProject(project);
				project.addProjectResource(pResource);
			}
		}
		
		projectRepository.add(project);
		
		return true;
	}
}
