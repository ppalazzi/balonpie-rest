package com.palazzisoft.balonpie.service.service.impl;

import static java.util.Optional.empty;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.FixtureDao;
import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.model.Fixture;
import com.palazzisoft.balonpie.service.service.FixtureService;

@Service("fixtureService")
@Transactional
public class FixtureServiceImpl implements FixtureService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private FixtureDao fixtureDao;

	@Override
	public FixtureDto saveFixture(Fixture fixture) {
		fixtureDao.saveFixture(fixture);
		return mapper.map(fixture, FixtureDto.class);
	}

	@Override
	public Optional<FixtureDto> getFixtureByTorneo(Integer torneoId) {
		Optional<Fixture> optionalFixture = fixtureDao.getFixtureByTorneo(torneoId);
		Optional<FixtureDto> responde = empty();

		if (optionalFixture.isPresent()) {
			responde = Optional.of(mapper.map(optionalFixture.get(), FixtureDto.class));
		}

		return responde;
	}

}
