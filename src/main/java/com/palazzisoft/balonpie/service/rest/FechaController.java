package com.palazzisoft.balonpie.service.rest;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.dto.FechaDto;
import com.palazzisoft.balonpie.service.service.EquipoService;
import com.palazzisoft.balonpie.service.service.FechaService;

@RestController
public class FechaController {

	private Logger LOG = LoggerFactory.getLogger(FechaController.class);

	@Autowired
	private FechaService fechaService;

	@Autowired
	private EquipoService equipoService;

	@RequestMapping(value = "/fecha/jugarFecha", method = POST)
	public ResponseEntity<FechaDto> jugarFecha(final @RequestBody FechaDto fechaDto) {
		LOG.info("Jugando fecha Número {}", fechaDto.getNumero());

		try {
			FechaDto fechaReturned = fechaService.jugarFecha(fechaDto);
			return new ResponseEntity<FechaDto>(fechaReturned, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Ocurrio un problema al jugar la fecha");
			return new ResponseEntity<>(NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = "/fecha/cambiarJugador/{equipoId}/{originalId}/{nuevoId}", method = POST)
	public ResponseEntity<EquipoDto> cambiarJugador(final @PathVariable Integer equipoId,
			@PathVariable Integer originalId, @PathVariable Integer nuevoId) {
		LOG.info("Cambiando Jugador de equipo {}", equipoId);

		try {
			EquipoDto equipoDto = equipoService.replaceJugador(equipoId, originalId, nuevoId);
			return new ResponseEntity<EquipoDto>(equipoDto, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			LOG.debug("No se pudo cambiar el jugador");
			return new ResponseEntity<>(NO_CONTENT);
		} catch (Exception e) {
			LOG.error("Ocurrio un error al cambiar los jugadores");
			return new ResponseEntity<>(NOT_ACCEPTABLE);
		}
	}

}
