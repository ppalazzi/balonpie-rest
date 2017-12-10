package com.palazzisoft.balonpie.service.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.EquipoDao;
import com.palazzisoft.balonpie.service.dao.JugadorDao;
import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.mapper.EquipoMapper;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.EquipoJugador;
import com.palazzisoft.balonpie.service.model.Jugador;
import com.palazzisoft.balonpie.service.service.EquipoService;

@Service("equipoService")
@Transactional
public class EquipoServiceImpl implements EquipoService {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EquipoDao equipoDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private EquipoMapper equipoMapper;

	@Autowired
	private JugadorDao jugadorDao;

	// TODO Remover este metodo, deberia recibir un dto y no un entity puro
	@Override
	public void saveEquipo(Equipo equipo) {
		equipoDao.saveEquipo(equipo);
	}

	@Override
	public EquipoDto saveEquipo(EquipoDto equipoDto) {
		Equipo equipo = equipoMapper.map(equipoDto);
		equipoDao.saveEquipo(equipo);
		return mapper.map(equipo, EquipoDto.class);
	}

	@Override
	public EquipoDto getEquipoById(Integer id) {
		Equipo equipo = equipoDao.findById(id);
		return mapper.map(equipo, EquipoDto.class);
	}

	@Override
	public EquipoDto replaceJugador(Integer equipoId, Integer idJugadorOriginal, Integer idJugadorNuevo) {
		Equipo equipo = equipoDao.findById(equipoId);

		if (equipo == null) {
			throw new IllegalArgumentException("El equipo es inválido");
		}

		Jugador jugadorOriginal = jugadorDao.findById(idJugadorOriginal);
		Jugador jugadorNuevo = jugadorDao.findById(idJugadorNuevo);

		// si el presupuesto del equipo da, entonces si hacer el reemplazo de
		// jugadores
		if ((equipo.getPresupuesto() + jugadorOriginal.getValor()) >= jugadorNuevo.getValor()) {
			Optional<EquipoJugador> equipoJugador = equipo.getEquipoJugadores().stream()
					.filter(ej -> ej.getJugador().getId().equals(idJugadorOriginal)).findFirst();

			if (equipoJugador.isPresent()) {
				equipo.getEquipoJugadores().remove(equipoJugador.get());
			}

			LOG.debug("" + equipo.getEquipoJugadores().size());

			EquipoJugador equipoJugadorNuevo = new EquipoJugador();
			equipoJugadorNuevo.setEquipo(equipo);
			equipoJugadorNuevo.setJugador(jugadorNuevo);
			equipo.getEquipoJugadores().add(equipoJugadorNuevo);
			equipoDao.saveEquipo(equipo);

			return mapper.map(equipo, EquipoDto.class);
		}

		throw new IllegalArgumentException("Presupuesto no válido para realizar esta acción");
	}

}
