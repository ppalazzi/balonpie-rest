package com.palazzisoft.balonpie.service.rest;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.dto.ParticipanteDto;
import com.palazzisoft.balonpie.service.exception.BalonpieException;
import com.palazzisoft.balonpie.service.service.ParticipanteService;

@RestController
public class ParticipanteController {

	private Logger LOG = LoggerFactory.getLogger(ParticipanteController.class);

	@Autowired
	private ParticipanteService participanteService;

	@RequestMapping(value = "/login/{email}/{password}", method = GET)
	public ResponseEntity<ParticipanteDto> login(@PathVariable String email, @PathVariable String password) {
		Optional<ParticipanteDto> participante = participanteService
				.getParticipanteByCredentials(email, password);
		if (!participante.isPresent()) {
			return new ResponseEntity<>(NOT_FOUND);
		}

		return new ResponseEntity<>(participante.get(), OK);
	}

	@RequestMapping(value = "/crearParticipante", method = POST)
	public ResponseEntity<ParticipanteDto> registrarParticipante(@RequestBody ParticipanteDto participanteDto) {
		try {
			ParticipanteDto dto = participanteService.saveParticipante(participanteDto);
			return new ResponseEntity<>(dto, OK);
		} catch (BalonpieException e) {
			LOG.error("Usuario ya existente");
			return new ResponseEntity<>(NOT_ACCEPTABLE);
		}
	}
		
	@RequestMapping(value = "/actualizarParticipante", method = POST)
	public ResponseEntity<ParticipanteDto> actualizarParticipante(@RequestBody ParticipanteDto participanteDto) {
		try {
			ParticipanteDto dto = participanteService.actualizarParticipante(participanteDto);
			return new ResponseEntity<>(dto, OK);
		} catch (BalonpieException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(NOT_ACCEPTABLE);
		}		
	}
}
