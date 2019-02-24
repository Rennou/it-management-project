package com.bdeb.application.projectmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@Table(name="project")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private Date dateIns;
	private String description;
	private Date endDate;
	private Date startDate;
	private String userIns;
	private Resource resource;
	private List<ProjectResource> projectResources;
	private List<Sprint> sprints;

	public Project() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getDateIns() {
		return this.dateIns;
	}

	public void setDateIns(Date dateIns) {
		this.dateIns = dateIns;
	}


	@Column(length=255)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	@Column(nullable=false, length=50)
	public String getUserIns() {
		return this.userIns;
	}

	public void setUserIns(String userIns) {
		this.userIns = userIns;
	}


	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="manager")
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}


	//bi-directional many-to-one association to ProjectResource
	@OneToMany(mappedBy="project", cascade={CascadeType.ALL})
	public List<ProjectResource> getProjectResources() {
		return this.projectResources;
	}

	public void setProjectResources(List<ProjectResource> projectResources) {
		this.projectResources = projectResources;
	}

	public ProjectResource addProjectResource(ProjectResource projectResource) {
		getProjectResources().add(projectResource);
		projectResource.setProject(this);

		return projectResource;
	}

	public ProjectResource removeProjectResource(ProjectResource projectResource) {
		getProjectResources().remove(projectResource);
		projectResource.setProject(null);

		return projectResource;
	}


	//bi-directional many-to-one association to Sprint
	@OneToMany(mappedBy="project")
	public List<Sprint> getSprints() {
		return this.sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}

	public Sprint addSprint(Sprint sprint) {
		getSprints().add(sprint);
		sprint.setProject(this);

		return sprint;
	}

	public Sprint removeSprint(Sprint sprint) {
		getSprints().remove(sprint);
		sprint.setProject(null);

		return sprint;
	}

}