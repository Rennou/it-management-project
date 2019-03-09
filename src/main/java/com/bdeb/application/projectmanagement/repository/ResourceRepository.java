package com.bdeb.application.projectmanagement.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bdeb.application.projectmanagement.model.Resource;

@Repository
public class ResourceRepository {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Resource> getList() {
		Query query = em.createQuery("SELECT res FROM Resource res");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Resource> getList(String roleCode) {
		Query query = em.createQuery("SELECT res FROM Resource res " + "where res.role = :e1");
		return query.setParameter("e1", roleCode).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Resource> getList(List<String> roles) {
		Query query = em.createQuery("SELECT res FROM Resource res " + "where res.role in :e1");
		return query.setParameter("e1", roles).getResultList();
	}

	@Transactional
	public void add(Resource resource) {
		em.persist(resource);
	}

	@Transactional
	public void update(Resource resource) {
		System.out.println("call update resources....");
		em.merge(resource);
	}

	@Transactional(readOnly = true)
	public Resource getResource(String id) {
		return em.find(Resource.class, id);
	}

}
