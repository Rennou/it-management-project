package com.bdeb.application.projectmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the step_status database table.
 * 
 */
@Entity
@Table(name="step_status")
@NamedQuery(name="StepStatus.findAll", query="SELECT s FROM StepStatus s")
public class StepStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dateIns;
	private String userComment;
	private Resource resource;
	private Status status;
	private Step step;
	private List<Task> tasks;

	public StepStatus() {
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


	@Column(length=255)
	public String getUserComment() {
		return this.userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}


	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="userIns", nullable=false)
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}


	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="statusId", nullable=false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	//bi-directional many-to-one association to Step
	@ManyToOne
	@JoinColumn(name="stepId", nullable=false)
	public Step getStep() {
		return this.step;
	}

	public void setStep(Step step) {
		this.step = step;
	}


	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="stepStatus")
	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setStepStatus(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setStepStatus(null);

		return task;
	}

}