package com.bdeb.application.projectmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sprint database table.
 * 
 */
@Entity
@Table(name="sprint")
@NamedQuery(name="Sprint.findAll", query="SELECT s FROM Sprint s")
public class Sprint implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date endDate;
	private int sprintNumber;
	private Date startDate;
	private String userIns;
	private Project project;
	private List<Task> tasks;

	public Sprint() {
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
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Column(nullable=false)
	public int getSprintNumber() {
		return this.sprintNumber;
	}

	public void setSprintNumber(int sprintNumber) {
		this.sprintNumber = sprintNumber;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
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


	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="projectId", nullable=false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="sprint")
	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setSprint(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setSprint(null);

		return task;
	}

}