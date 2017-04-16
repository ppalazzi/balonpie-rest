package com.palazzisoft.balonpie.service.rest;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

		return new ResponseEntity<ParticipanteDto>(participante.get(), OK);
	}

	@RequestMapping(value = "/crearParticipante", method = POST)
	public ResponseEntity<ParticipanteDto> registrarParticipante(@Valid ParticipanteDto participanteDto) {
		try {
			ParticipanteDto dto = participanteService.saveParticipante(participanteDto);
			return new ResponseEntity<ParticipanteDto>(dto, OK);
		} catch (BalonpieException e) {
			LOG.error("Usuario ya existente");
			return new ResponseEntity<>(NOT_ACCEPTABLE);
		}
	}
}
