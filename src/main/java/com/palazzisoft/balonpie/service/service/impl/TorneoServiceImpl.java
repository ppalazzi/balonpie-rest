package com.palazzisoft.balonpie.service.service.impl;

import static jersey.repackaged.com.google.common.collect.Lists.newArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.TorneoDao;
import com.palazzisoft.balonpie.service.dto.TorneoDto;
import com.palazzisoft.balonpie.service.model.Torneo;
import com.palazzisoft.balonpie.service.model.enumeration.EEstado;
import com.palazzisoft.balonpie.service.service.TorneoService;

@Service("torneoService")
@Transactional
public class TorneoServiceImpl implements TorneoService {

	private Logger LOG = LoggerFactory.getLogger(TorneoServiceImpl.class);

	@Autowired
	private TorneoDao torneoDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<TorneoDto> obtenerParticipantesPorTorneo(Integer participanteId) {
		LOG.info("Obteniendo torneos de participante {}", participanteId);
		
		List<Torneo> torneos = torneoDao.obtenerTorneosDeParticipante(participanteId);
		List<TorneoDto> torneosDto = newArrayList();
		mapper.map(torneos, torneosDto);
		return torneosDto;
	}

	@Override
	public TorneoDto guardarTorneo(TorneoDto torneoDto) {
		LOG.info("Guardando nuevo Torneo {} ", torneoDto);
		
		Torneo torneo = mapper.map(torneoDto, Torneo.class);		
		torneo.setEstado(EEstado.ACTIVO.getEstado());		
		Calendar c = Calendar.getInstance();
		torneo.setFechaCreacion(c.getTime());
		torneo.setFechaInicio(torneo.getFechaCreacion());		
		c.add(Calendar.DATE, 30);				
		torneo.setFechaFin(c.getTime());
		
		torneoDao.crearTorneo(torneo);
				
		return mapper.map(torneo, TorneoDto.class);
	}
}
