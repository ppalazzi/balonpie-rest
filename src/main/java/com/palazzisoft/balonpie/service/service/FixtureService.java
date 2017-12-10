package com.palazzisoft.balonpie.service.service;

import java.util.Optional;

import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.model.Fixture;

public interface FixtureService {

	FixtureDto saveFixture(Fixture fixture);

	Optional<FixtureDto> getFixtureByTorneo(Integer torneoId);
}
