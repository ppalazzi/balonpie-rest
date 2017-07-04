package com.palazzisoft.balonpie.service.service;

import java.util.List;

import com.palazzisoft.balonpie.service.dto.TorneoDto;
import com.palazzisoft.balonpie.service.model.Torneo;

public interface TorneoService {

	List<TorneoDto> obtenerParticipantesPorTorneo(Integer participanteId);

	TorneoDto guardarTorneo(TorneoDto torneoDto);
}
