package com.bdeb.application.projectmanagement.forms;

import java.util.ArrayList;
import java.util.List;

import com.bdeb.application.projectmanagement.model.Project;
import com.bdeb.application.projectmanagement.model.Resource;

public class ProjectForm {

	Project project = new Project();
	
	List <Resource> managers =  new ArrayList<Resource>();
	List <Resource> resources =  new ArrayList<Resource>();
	
	String managerId="";
	List<String> ressourceIds = new ArrayList<String>();

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Resource> getManagers() {
		return managers;
	}

	public void setManagers(List<Resource> managers) {
		this.managers = managers;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public List<String> getRessourceIds() {
		return ressourceIds;
	}

	public void setRessourceIds(List<String> ressourceIds) {
		this.ressourceIds = ressourceIds;
	}
	
	
}
