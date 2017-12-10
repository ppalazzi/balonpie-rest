package com.palazzisoft.balonpie.service.dao.impl;

import java.util.Optional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.FixtureDao;
import com.palazzisoft.balonpie.service.model.Fixture;
import com.palazzisoft.balonpie.service.model.Torneo;

@Repository("fixtureDao")
public class FixtureDaoImpl extends AbstractDao implements FixtureDao {

	@Override
	public void saveFixture(Fixture fixture) {
		getSession().persist(fixture);
	}

	@Override
	public Optional<Fixture> getFixtureByTorneo(Integer torneoId) {
		Query<Fixture> query = getSession().createQuery("FROM Fixture f where f.torneo.id = :id", Fixture.class);
		query.setParameter("id", torneoId);

		return query.uniqueResultOptional();
	}

}
