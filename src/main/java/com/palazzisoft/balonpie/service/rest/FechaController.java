package com.palazzisoft.balonpie.service.rest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.dto.FechaDto;

@RestController
public class FechaController {

	private Logger LOG = LoggerFactory.getLogger(FechaController.class);
	
	@RequestMapping(value = "/fecha/jugarFecha", method = POST)
	public FechaDto jugarFecha(final @RequestBody FechaDto fechaDto) {
		LOG.info("Jugando fecha Número {}", fechaDto.getNumero());
		
		
		
		return null;
	}
}
