package com.palazzisoft.balonpie.service.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.TorneoDao;
import com.palazzisoft.balonpie.service.model.Torneo;

@Repository("torneoDao")
public class TorneoDaoImpl extends AbstractDao implements TorneoDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Torneo> getTorneosByParticipante(Integer participanteId) {
		Query query = em
				.createQuery("FROM Torneo t where t.participante.id = :participanteId and t.estado = 1");
		query.setParameter("participanteId", participanteId);

		return query.getResultList();
	}

	@Override
	public boolean isDescripcionAvailable(String descripcion) {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Torneo> query = builder.createQuery(Torneo.class);

			Root<Torneo> root = query.from(Torneo.class);
			query.select(root).where(builder.equal(root.get("descripcion"), descripcion));

			return Optional.ofNullable(em.createQuery(query).getSingleResult()).isPresent();
		}
		catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public void saveTorneo(Torneo torneo) {
		this.persist(torneo);
	}

	@Override
	public Torneo findById(Integer torneoId) {
		Torneo torneo = em.find(Torneo.class, torneoId);
		return torneo;
	}
}
