package com.palazzisoft.balonpie.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.EquipoJugador;
import com.palazzisoft.balonpie.service.model.Jugador;

@Component
public class EquipoMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Equipo map(EquipoDto equipoDto) {
		Equipo equipo = mapper.map(equipoDto, Equipo.class);
		equipo.getEquipoJugadores().clear();
		
		equipoDto.getJugadores().stream().forEach(j -> {
			Jugador jugador = mapper.map(j, Jugador.class);
			EquipoJugador equipoJugador = new EquipoJugador();
			equipoJugador.setEquipo(equipo);
			equipoJugador.setJugador(jugador);
			equipo.getEquipoJugadores().add(equipoJugador);
		});
		
		return equipo;
	}
}
