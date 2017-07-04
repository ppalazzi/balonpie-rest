package com.palazzisoft.balonpie.service.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.TorneoDao;
import com.palazzisoft.balonpie.service.model.Torneo;

@Repository("torneoDao")
public class TorneoDaoImpl extends AbstractDao implements TorneoDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Torneo> obtenerTorneosDeParticipante(Integer participanteId) {
		Query<Torneo> query = getSession().createQuery("FROM Torneo t where t.participante.id = :participanteId and t.estado = 1");
		query.setParameter("participanteId", participanteId);
		

		return query.getResultList();
	}

	@Override
	public void crearTorneo(Torneo torneo) {
		this.persist(torneo);
	}
}
