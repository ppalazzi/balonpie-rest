package com.palazzisoft.balonpie.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDao {

    @PersistenceContext
    protected EntityManager em;

	public void persist(Object entity) {
		em.persist(entity);
	}

	public void delete(Object entity) {
        em.remove(entity);
    }

	public void saveOrUpdate(Object entity) {
		em.merge(entity);
	}

	public void merge(Object entity) {
		em.merge(entity);
	}

	public EntityManager getEntityManager() {
	    return em;
    }
}
