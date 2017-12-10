package com.palazzisoft.balonpie.service.rest;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.dto.EquiposEstadisticasDto;
import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.service.PosicionesService;

@RestController
public class FixtureController {

	private Logger LOG = LoggerFactory.getLogger(FixtureController.class);

	@Autowired
	private PosicionesService posicionesService;
	
	@RequestMapping(value = "/fixture/posiciones", method = RequestMethod.GET)
	public ResponseEntity<List<EquiposEstadisticasDto>> posiciones(final @RequestBody FixtureDto fixtureDto) {
		LOG.info("Calculando estadisticas para el Fixture");
		
		try {
			List<EquiposEstadisticasDto> estadisticas = posicionesService.getPosicionesByFixture(fixtureDto);
			return new ResponseEntity<List<EquiposEstadisticasDto>>(estadisticas, HttpStatus.OK);
		}
		catch (Exception e) {
			LOG.error("Ocurrio un problema al jugar la fecha");
			return new ResponseEntity<>(NOT_ACCEPTABLE);			
		}
	}
}
