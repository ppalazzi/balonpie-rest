package com.palazzisoft.balonpie.service.service;

import java.util.List;

import com.palazzisoft.balonpie.service.dto.EquiposEstadisticasDto;
import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.model.Fixture;

public interface PosicionesService {

	List<EquiposEstadisticasDto> getPosicionesByFixture(Fixture fixture);

}
