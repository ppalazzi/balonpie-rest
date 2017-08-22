package com.palazzisoft.balonpie.service.dao;

import java.util.List;

import com.palazzisoft.balonpie.service.model.Jugador;

public interface JugadorDao {

	void crearJugador(Jugador jugador);

	List<Jugador> getJugadoresByType(Integer type);
}
