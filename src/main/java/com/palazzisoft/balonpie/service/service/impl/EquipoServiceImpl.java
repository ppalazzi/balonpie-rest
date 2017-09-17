package com.palazzisoft.balonpie.service.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.EquipoDao;
import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.mapper.EquipoMapper;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.service.EquipoService;

@Service("equipoService")
@Transactional
public class EquipoServiceImpl implements EquipoService {

	@Autowired
	private EquipoDao equipoDao;

    @Autowired
    private ModelMapper mapper;	
    
    @Autowired
    private EquipoMapper equipoMapper;
	
    // TODO Remover este metodo, deberia recibir un dto y no un entity puro
	@Override
	public void saveEquipo(Equipo equipo) {
		equipoDao.saveEquipo(equipo);
	}
	
	@Override
	public void saveEquipo(EquipoDto equipoDto) {
		Equipo equipo = equipoMapper.map(equipoDto);
		equipoDao.saveEquipo(equipo);
		mapper.map(equipo, equipoDto);
	}

	@Override
	public EquipoDto getEquipoById(Integer id) {
		Equipo equipo = equipoDao.findById(id);		
		return mapper.map(equipo, EquipoDto.class);		
	}

}
