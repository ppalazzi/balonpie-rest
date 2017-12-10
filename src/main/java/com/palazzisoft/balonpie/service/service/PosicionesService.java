package com.palazzisoft.balonpie.service.service;

import java.util.List;

import com.palazzisoft.balonpie.service.dto.EquiposEstadisticasDto;
import com.palazzisoft.balonpie.service.dto.FixtureDto;

public interface PosicionesService {

	List<EquiposEstadisticasDto> getPosicionesByFixture(FixtureDto fixture);

}
