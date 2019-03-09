package com.bdeb.application.projectmanagement.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bdeb.application.projectmanagement.model.Project;
import com.bdeb.application.projectmanagement.model.Resource;

@Repository
public class ProjectRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Project> getList() {
		Query query = em.createQuery("SELECT p FROM Project p");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Project> getList(String username) {
		Query query = em.createQuery("SELECT DISTINCT pr.project FROM ProjectResource pr where"
				+ " pr.resource.username = :e1");
		return query.setParameter("e1", username).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Project> getListByManger(String username) {
		Query query = em.createQuery("SELECT p FROM Project p where"
				+ " p.resource.username = :e1");
		return query.setParameter("e1", username).getResultList();
	}
	
	@Transactional
	public void add(Project project) {
		em.persist(project);
	}
	
	
}
