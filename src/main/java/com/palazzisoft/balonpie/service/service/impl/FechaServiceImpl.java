package com.palazzisoft.balonpie.service.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.FechaDao;
import com.palazzisoft.balonpie.service.dto.FechaDto;
import com.palazzisoft.balonpie.service.model.Fecha;
import com.palazzisoft.balonpie.service.service.FechaService;
import com.palazzisoft.balonpie.service.service.PartidoService;

@Service("fechaService")
@Transactional
public class FechaServiceImpl implements FechaService {

	@Autowired
	private FechaDao fechaDao;

	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public FechaDto jugarFecha(FechaDto fechaDto) {
		Fecha fecha = fechaDao.getFechaById(fechaDto.getId());

		fecha.getPartidos().stream().forEach(p -> {
			if (!p.isJugado()) {
				partidoService.jugarPartido(p);
			}
		});
		
		fechaDao.saveFecha(fecha);
		
		return mapper.map(fecha, FechaDto.class);
	}

	@Override
	public void saveFecha(Fecha fecha) {
		fechaDao.saveFecha(fecha);
	}

}
