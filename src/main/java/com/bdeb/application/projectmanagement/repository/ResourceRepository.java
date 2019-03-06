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

	@Transactional
	public void add(Resource resource) {
		try {

			em.persist(resource);

		} catch (RuntimeException e) {
			e.printStackTrace();
			// if ( tx != null && tx.isActive() ) tx.rollback();

		}
	}

	@Transactional
	public void update(Resource resource) {
		System.out.println("call update resources....");
		em.merge(resource);
	}

}
