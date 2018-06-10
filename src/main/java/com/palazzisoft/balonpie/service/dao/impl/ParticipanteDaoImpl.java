package com.palazzisoft.balonpie.service.dao.impl;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.ParticipanteDao;
import com.palazzisoft.balonpie.service.model.Participante;

@Repository("participanteDao")
public class ParticipanteDaoImpl extends AbstractDao implements ParticipanteDao {

	@Override
	public Participante findById(Integer id) {
		return getSession().get(Participante.class, id);
	}

	@Override
	public Optional<Participante> findParticipanteByCredentials(String user, String pass) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Participante> query = builder.createQuery(Participante.class);

		Root<Participante> root = query.from(Participante.class);

		Predicate userNamePredicate = builder.equal(root.get("email"), user);
		Predicate passwordPredicate = builder.equal(root.get("password"), pass);
		Predicate wherePredicate = builder.and(userNamePredicate, passwordPredicate);

		query.select(root).where(wherePredicate);

		return getSession().createQuery(query).uniqueResultOptional();
	}

	@Override
	public Optional<Participante> findParticipanteByEmail(final String email) {
		Query<Participante> query = getSession().createQuery("FROM Participante where email = :email",
				Participante.class);
		query.setParameter("email", email);

		return query.uniqueResultOptional();
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
