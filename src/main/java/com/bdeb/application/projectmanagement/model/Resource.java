package com.bdeb.application.projectmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the resource database table.
 * 
 */
@Entity
@Table(name="resource")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private Date dateIns;
	private String firstName;
	private String lastName;
	private String userIns;
	private List<Project> projects;
	private List<ProjectResource> projectResources;
	private List<StepStatus> stepStatuses;

	public Resource() {
	}


	@Id
	@Column(unique=true, nullable=false, length=50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getDateIns() {
		return this.dateIns;
	}

	public void setDateIns(Date dateIns) {
		this.dateIns = dateIns;
	}


	@Column(nullable=false, length=50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(nullable=false, length=50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Column(nullable=false, length=50)
	public String getUserIns() {
		return this.userIns;
	}

	public void setUserIns(String userIns) {
		this.userIns = userIns;
	}


	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="resource")
	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setResource(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setResource(null);

		return project;
	}


	//bi-directional many-to-one association to ProjectResource
	@OneToMany(mappedBy="resource")
	public List<ProjectResource> getProjectResources() {
		return this.projectResources;
	}

	public void setProjectResources(List<ProjectResource> projectResources) {
		this.projectResources = projectResources;
	}

	public ProjectResource addProjectResource(ProjectResource projectResource) {
		getProjectResources().add(projectResource);
		projectResource.setResource(this);

		return projectResource;
	}

	public ProjectResource removeProjectResource(ProjectResource projectResource) {
		getProjectResources().remove(projectResource);
		projectResource.setResource(null);

		return projectResource;
	}


	//bi-directional many-to-one association to StepStatus
	@OneToMany(mappedBy="resource")
	public List<StepStatus> getStepStatuses() {
		return this.stepStatuses;
	}

	public void setStepStatuses(List<StepStatus> stepStatuses) {
		this.stepStatuses = stepStatuses;
	}

	public StepStatus addStepStatus(StepStatus stepStatus) {
		getStepStatuses().add(stepStatus);
		stepStatus.setResource(this);

		return stepStatus;
	}

	public StepStatus removeStepStatus(StepStatus stepStatus) {
		getStepStatuses().remove(stepStatus);
		stepStatus.setResource(null);

		return stepStatus;
	}

}