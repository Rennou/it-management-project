package com.bdeb.application.projectmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@Table(name="status")
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private Date dateIns;
	private String description;
	private String userIns;
	private List<StepStatus> stepStatuses;

	public Status() {
	}


	@Id
	@Column(unique=true, nullable=false, length=10)
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


	@Column(nullable=false, length=50)
	public String getUserIns() {
		return this.userIns;
	}

	public void setUserIns(String userIns) {
		this.userIns = userIns;
	}


	//bi-directional many-to-one association to StepStatus
	@OneToMany(mappedBy="status")
	public List<StepStatus> getStepStatuses() {
		return this.stepStatuses;
	}

	public void setStepStatuses(List<StepStatus> stepStatuses) {
		this.stepStatuses = stepStatuses;
	}

	public StepStatus addStepStatus(StepStatus stepStatus) {
		getStepStatuses().add(stepStatus);
		stepStatus.setStatus(this);

		return stepStatus;
	}

	public StepStatus removeStepStatus(StepStatus stepStatus) {
		getStepStatuses().remove(stepStatus);
		stepStatus.setStatus(null);

		return stepStatus;
	}

}