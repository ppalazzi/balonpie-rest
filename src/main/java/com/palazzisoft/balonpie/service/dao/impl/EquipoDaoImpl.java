package com.palazzisoft.balonpie.service.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.EquipoDao;
import com.palazzisoft.balonpie.service.model.Equipo;

@Repository("equipoDao")
public class EquipoDaoImpl extends AbstractDao implements EquipoDao {

	// TODO esto tiene que volar, debe recibir un dto no un entity puro
	@Override
	public void saveEquipo(Equipo equipo) {
		persist(equipo);
	}

	@Override
	public List<Equipo> getAvailableEquipos() {
		Query<Equipo> query = getSession().createQuery(
				"FROM Equipo e where e.estado = 1 and e.participante is null and size(e.torneos) = 0", Equipo.class);
		return query.getResultList();
	}

	@Override
	public Equipo findById(Integer id) {
		return getSession().find(Equipo.class, id);
	}

	@Override
	public void mergeEquipo(Equipo equipo) {
		merge(equipo);
	}
}
