package com.palazzisoft.balonpie.service.service;

import java.util.List;

import com.palazzisoft.balonpie.service.dto.JugadorDto;
import com.palazzisoft.balonpie.service.model.Jugador;

public interface JugadorService {

	JugadorDto saveJugador(JugadorDto jugador);

	List<Jugador> getJugadorByType(Integer type);

	JugadorDto getJugadoresById(Integer id);
}
