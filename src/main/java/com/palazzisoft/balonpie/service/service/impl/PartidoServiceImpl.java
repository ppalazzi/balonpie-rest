package com.palazzisoft.balonpie.service.service.impl;

import java.util.Random;

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

        Random rand = new Random();

		int golesLocal = rand.nextInt(5);
		int golesVisitante = rand.nextInt(4);

		partido.setGolesLocal(golesLocal);
		partido.setGolesVisitante(golesVisitante);

		partido.setJugado(true);
	}

}
