package com.palazzisoft.balonpie.service.rest;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.dto.TorneoDto;
import com.palazzisoft.balonpie.service.service.TorneoService;

@RestController
public class TorneoController {

	@Autowired
	private TorneoService torneoService;

	private Logger LOG = LoggerFactory.getLogger(TorneoController.class);

	@RequestMapping(value = "/torneo/{participanteId}", method = GET)
	public ResponseEntity<List<TorneoDto>> torneosPorParticipante(final @PathVariable Integer participanteId) {
		LOG.info("Trayendo torneo del participante {}", participanteId);

		List<TorneoDto> torneos = torneoService.obtenerParticipantesPorTorneo(participanteId);

		if (torneos.isEmpty()) {
			return new ResponseEntity<>(NO_CONTENT);
		}

		return new ResponseEntity<List<TorneoDto>>(torneos, OK);
	}

	@RequestMapping(value = "/crearTorneo", method = RequestMethod.POST)
	public ResponseEntity<TorneoDto> crearTorneo(@RequestBody TorneoDto torneoDto) {
		LOG.info("Creando Torneo {}", torneoDto);

		TorneoDto nuevoTorneo = torneoService.guardarTorneo(torneoDto);
		
		return new ResponseEntity<TorneoDto>(nuevoTorneo, OK);
	}
}
