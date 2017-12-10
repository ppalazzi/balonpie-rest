package com.palazzisoft.balonpie.service.service;

import java.util.List;

import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.dto.TorneoDto;
import com.palazzisoft.balonpie.service.exception.BalonpieException;

public interface TorneoService {

	List<TorneoDto> getTorneosByParticipante(Integer participanteId);

	TorneoDto createTorneo(TorneoDto torneoDto) throws BalonpieException;

	FixtureDto buildFixtureByTorneo(Integer torneoId);

	TorneoDto getTorneoById(Integer id);
}
