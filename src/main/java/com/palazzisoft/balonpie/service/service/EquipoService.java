package com.palazzisoft.balonpie.service.service;

import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.model.Equipo;

public interface EquipoService {

	void saveEquipo(Equipo equipo);

	EquipoDto getEquipoById(Integer id);

	EquipoDto saveEquipo(EquipoDto equipoDto);

	/**
	 * Si el presupuesto lo permite, vende el jugador original y compra el nuevo 
	 */
	EquipoDto replaceJugador(Integer equipoDto, Integer idJugadorOriginal, Integer idJugadorNuevo);
}
