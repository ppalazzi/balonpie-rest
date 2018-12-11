package com.palazzisoft.balonpie.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.FechaDao;
import com.palazzisoft.balonpie.service.model.Fecha;

import javax.persistence.EntityManagerFactory;

@Repository("fechaDao")
public class FechaDaoImpl extends AbstractDao implements FechaDao {

	@Override
	public Fecha getFechaById(Integer id) {
		return em.find(Fecha.class, id);
	}

	@Override
	public void saveFecha(Fecha fecha) {
		em.persist(fecha);
	}

}
