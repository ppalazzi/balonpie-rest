package com.palazzisoft.balonpie.service.dao.impl;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.ParticipanteDao;
import com.palazzisoft.balonpie.service.model.Participante;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository("participanteDao")
public class ParticipanteDaoImpl extends AbstractDao implements ParticipanteDao {

	@Override
	public Participante findById(Integer id) {
		return em.find(Participante.class, id);
	}

	@Override
	public Optional<Participante> findParticipanteByCredentials(String user, String pass) {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Participante> query = builder.createQuery(Participante.class);

			Root<Participante> root = query.from(Participante.class);

			Predicate userNamePredicate = builder.equal(root.get("email"), user);
			Predicate passwordPredicate = builder.equal(root.get("password"), pass);
			Predicate wherePredicate = builder.and(userNamePredicate, passwordPredicate);

			query.select(root).where(wherePredicate);

			return Optional.ofNullable(em.createQuery(query).getSingleResult());
		}
		catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Participante> findParticipanteByEmail(final String email) {
		TypedQuery<Participante> query = em.createQuery("FROM Participante where email = :email",
				Participante.class);
		try {
			query.setParameter("email", email);

			return Optional.ofNullable(query.getSingleResult());
		}
		catch(NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public void saveParticipante(Participante participante) {
		persist(participante);
	}

	@Override
	public void updateParticipante(Participante participante) {		
		saveOrUpdate(participante);
	}
}
