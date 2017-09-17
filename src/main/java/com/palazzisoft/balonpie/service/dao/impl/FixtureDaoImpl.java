package com.palazzisoft.balonpie.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.FixtureDao;
import com.palazzisoft.balonpie.service.model.Fixture;
@Repository("fixtureDao")
public class FixtureDaoImpl extends AbstractDao implements FixtureDao {

	@Override
	public void saveFixture(Fixture fixture) {
		getSession().persist(fixture);
	}

}
