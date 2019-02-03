package com.palazzisoft.balonpie.service.rest;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.dto.JugadorDto;
import com.palazzisoft.balonpie.service.service.JugadorService;

@RestController
public class JugadorController {

	private Logger LOG = LoggerFactory.getLogger(JugadorController.class);
	
	@Autowired
	private JugadorService jugadorService;
	
	@RequestMapping(value = "/jugador/{type}/{budget}", method = GET)
	public ResponseEntity<List<JugadorDto>> getJugadoresByTypeAndBudget(final @PathVariable Integer type, 
			final @PathVariable Integer budget) {
		LOG.info("Trayendo Jugadores de tipo {} and con un presupuesto de {}", type, budget);
		
		List<JugadorDto> jugadores = jugadorService.getJugadorByTypeAndBudget(type, budget);
		
		if (jugadores.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(jugadores, OK);
	}
	
}
