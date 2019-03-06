package com.bdeb.application.projectmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdeb.application.projectmanagement.model.Project;
import com.bdeb.application.projectmanagement.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserService userService;
	
	
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
}
