package com.palazzisoft.balonpie.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.FechaDao;
import com.palazzisoft.balonpie.service.model.Fecha;

@Repository("fechaDao")
public class FechaDaoImpl extends AbstractDao implements FechaDao {

	@Override
	public Fecha getFechaById(Integer id) {
		return getSession().find(Fecha.class, id);
	}

	@Override
	public void saveFecha(Fecha fecha) {
		this.getSession().persist(fecha);
	}

}
