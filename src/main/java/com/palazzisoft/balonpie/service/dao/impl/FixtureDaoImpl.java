package com.palazzisoft.balonpie.service.dao.impl;

import java.util.Optional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.FixtureDao;
import com.palazzisoft.balonpie.service.model.Fixture;
import com.palazzisoft.balonpie.service.model.Torneo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import static java.util.Optional.ofNullable;

@Repository("fixtureDao")
public class FixtureDaoImpl extends AbstractDao implements FixtureDao {

	@Override
	public void saveFixture(Fixture fixture) {
		em.persist(fixture);
	}

	@Override
	public Optional<Fixture> getFixtureByTorneo(Integer torneoId) {
		TypedQuery<Fixture> query = em.createQuery("FROM Fixture f where f.torneo.id = :id", Fixture.class);
		query.setParameter("id", torneoId);

		return ofNullable(query.getSingleResult());
	}

}
