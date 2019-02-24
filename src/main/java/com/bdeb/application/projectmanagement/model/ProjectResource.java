package com.bdeb.application.projectmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the project_resource database table.
 * 
 */
@Entity
@Table(name="project_resource")
@NamedQuery(name="ProjectResource.findAll", query="SELECT p FROM ProjectResource p")
public class ProjectResource implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dateIns;
	private String userIns;
	private Project project;
	private Resource resource;

	public ProjectResource() {
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


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getDateIns() {
		return this.dateIns;
	}

	public void setDateIns(Date dateIns) {
		this.dateIns = dateIns;
	}


	@Column(nullable=false, length=50)
	public String getUserIns() {
		return this.userIns;
	}

	public void setUserIns(String userIns) {
		this.userIns = userIns;
	}


	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="projectId", nullable=false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="resourceId", nullable=false)
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}