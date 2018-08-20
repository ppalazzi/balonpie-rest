package com.palazzisoft.balonpie.service.rest;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.dto.TorneoDto;
import com.palazzisoft.balonpie.service.exception.BalonpieException;
import com.palazzisoft.balonpie.service.service.TorneoService;

@RestController
public class TorneoController {

	@Autowired
	private TorneoService torneoService;

	private Logger LOG = LoggerFactory.getLogger(TorneoController.class);

	@RequestMapping(value = "/torneo/{participanteId}", method = GET)
	public ResponseEntity<List<TorneoDto>> torneosByParticipante(final @PathVariable Integer participanteId) {
		LOG.info("Trayendo torneo del participante {}", participanteId);

		List<TorneoDto> torneos = torneoService.getTorneosByParticipante(participanteId);

		if (torneos.isEmpty()) {
			return new ResponseEntity<>(NO_CONTENT);
		}

		return new ResponseEntity<List<TorneoDto>>(torneos, OK);
	}

	@RequestMapping(value = "/crearTorneo", method = POST)
	public ResponseEntity createTorneo(@RequestBody TorneoDto torneoDto) {
		LOG.info("Creando Torneo {}", torneoDto);

		TorneoDto nuevoTorneo;
		try {
			nuevoTorneo = torneoService.createTorneo(torneoDto);
		} catch (BalonpieException e) {
			return ResponseEntity.status(FORBIDDEN).body(e.getMessage());
		}

		return ResponseEntity.status(OK).body(nuevoTorneo);
	}

	@RequestMapping(value = "/arrancarTorneo/{torneoId}", method = GET)
	public ResponseEntity<FixtureDto> startUpTorneo(@PathVariable Integer torneoId) {
		LOG.info("Generando el fixture para el torneo {}", torneoId);

		FixtureDto fixture = torneoService.buildFixtureByTorneo(torneoId);
		if (fixture == null) {
			return new ResponseEntity<>(NO_CONTENT);
		}

		return ResponseEntity.status(OK).body(fixture);
	}

	@RequestMapping(value = "/torneo/nombreValido/{name}", method = GET)
	public ResponseEntity<Boolean> nombreDeTorneoValido(@PathVariable String name) {
		LOG.info("Chequeando nombre del Equipo");				
		return ResponseEntity.status(OK).body(torneoService.isNameValid(name));
	}

	@RequestMapping(value = "/torneo/remove/{torneoId}", method = GET)
	public ResponseEntity<TorneoDto> removerTorneo(@PathVariable Integer torneoId) {
		LOG.info("Removiendo Torneo con id {}", torneoId);
		
		TorneoDto torneoDto = torneoService.removeTournament(torneoId);
		if (torneoDto == null) {
			return new ResponseEntity<>(NO_CONTENT);
		}
		
		return ResponseEntity.status(OK).body(torneoDto);
	}
	
	@RequestMapping(value = "/torneo/traer/{torneoId}", method = GET)
	public ResponseEntity<TorneoDto> torneoPorId(@PathVariable Integer torneoId) {
		LOG.info("Trayendo Torneo por ID {}", torneoId);
		
		TorneoDto torneoDto = torneoService.getTorneoById(torneoId);
		if (torneoDto == null) {
			return new ResponseEntity<>(NO_CONTENT);
		}
		
		return ResponseEntity.status(OK).body(torneoDto);
	}
}
