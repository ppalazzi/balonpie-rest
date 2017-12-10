package com.palazzisoft.balonpie.service.dao;

import java.util.Optional;

import com.palazzisoft.balonpie.service.model.Fixture;

public interface FixtureDao {

	void saveFixture(Fixture fixture);

	Optional<Fixture> getFixtureByTorneo(Integer torneoId);
}
