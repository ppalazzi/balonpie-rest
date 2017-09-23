package com.palazzisoft.balonpie.service.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.model.Partido;
import com.palazzisoft.balonpie.service.service.PartidoService;

@Service("partidoService")
@Transactional
public class PartidoServiceImpl implements PartidoService {

	@Override
	public void jugarPartido(Partido partido) {
		// TODO pensar una buena implementacion	
		partido.setJugado(true);
	}

}
