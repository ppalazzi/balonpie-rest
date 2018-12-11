package com.palazzisoft.balonpie.service.service.impl;

import static com.google.common.collect.Lists.newArrayList;
import static com.palazzisoft.balonpie.service.model.enumeration.EEstado.ACTIVO;
import static java.lang.System.currentTimeMillis;
import static java.util.Collections.shuffle;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.EquipoDao;
import com.palazzisoft.balonpie.service.dao.FixtureDao;
import com.palazzisoft.balonpie.service.dao.TorneoDao;
import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.dto.TorneoDto;
import com.palazzisoft.balonpie.service.exception.BalonpieException;
import com.palazzisoft.balonpie.service.factory.FixtureFactory;
import com.palazzisoft.balonpie.service.mapper.EquipoMapper;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.Fixture;
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
	private EquipoDao equipoDao;

	@Autowired
	private FixtureDao fixtureDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private EquipoMapper equipoMapper;
	
	@Value("${cantidad.equipos.por.torneo}")
	private int cantidadEquiposPorTorneo;

	@Value("${cantidad.jugadores.equipos}")
	private int amountJugadoresByEquipo;

	@Value("${equipo.presupuesto.inicial}")
	private Long amountInitialBudget;

	@Value("${equipo.puntos.inicial}")
	private Long amountInitialPoints;

	@Override
	public List<TorneoDto> getTorneosByParticipante(Integer participanteId) {
		LOG.info("Obteniendo torneos de participante {}", participanteId);

		List<Torneo> torneos = torneoDao.getTorneosByParticipante(participanteId);
		List<TorneoDto> torneosDto = newArrayList();
		
		torneos.stream().forEach(t -> {
			TorneoDto dto = mapper.map(t, TorneoDto.class);
			torneosDto.add(dto);
		});
		
		mapper.map(torneos, torneosDto);
		return torneosDto;
	}

	@Override
	public TorneoDto createTorneo(TorneoDto torneoDto) throws BalonpieException {
		validateNewTorneo(torneoDto);

		LOG.info("Guardando nuevo Torneo {} ", torneoDto);

		Torneo torneo = mapper.map(torneoDto, Torneo.class);
		Equipo equipoMapped = equipoMapper.map(torneoDto.getEquipos().get(0));
		torneo.getEquipos().clear();
		torneo.getEquipos().add(equipoMapped);
		torneo.getEquipos().addAll(getAvailableEquiposSortedWithCount(torneo));

		equipoDao.saveEquipo(torneo.getEquipos().get(0));
		torneoDao.saveTorneo(torneo);

		return mapper.map(torneo, TorneoDto.class);
	}

	@Override
	public FixtureDto buildFixtureByTorneo(Integer torneoId) {
		Torneo torneo = this.torneoDao.findById(torneoId);
		List<Equipo> equipos = torneo.getEquipos();

		Fixture fixture = FixtureFactory.crearFixture(equipos);
		fixture.setTorneo(torneo);
		torneo.setFixture(fixture);
		fixtureDao.saveFixture(fixture);
		torneoDao.saveTorneo(torneo);

		return mapper.map(fixture, FixtureDto.class);
	}

	@Override
	public TorneoDto getTorneoById(Integer torneoId) {
		Torneo torneo = torneoDao.findById(torneoId);
		TorneoDto torneoDto = mapper.map(torneo, TorneoDto.class);

		FixtureDto fixtureDto = mapper.map(torneo.getFixture(), FixtureDto.class);
		torneoDto.setFixture(fixtureDto);

		return torneoDto;
	}

	@Override
	public Boolean isNameValid(String name) {
		return torneoDao.isDescripcionAvailable(name);
	}	
	
	@Override
	public TorneoDto removeTournament(Integer torneoId) {
		Torneo torneo = torneoDao.findById(torneoId);
		torneo.setEstado(EEstado.INACTIVO.getEstado());
		torneoDao.saveTorneo(torneo);
		return mapper.map(torneo, TorneoDto.class);
	}
	
	private List<Equipo> getAvailableEquiposSortedWithCount(Torneo torneo) {
		setInitialDataToTorneo(torneo);
		setInitialDataToEquipo(torneo);
		List<Equipo> equipos = this.equipoDao.getAvailableEquipos();

		shuffle(equipos, new Random(currentTimeMillis()));

		return equipos.stream().limit(cantidadEquiposPorTorneo - torneo.getEquipos().size()).collect(toList());
	}

	private void validateNewTorneo(TorneoDto torneo) throws BalonpieException {
		Optional<String> message = Optional.empty();

		if (torneoDao.isDescripcionAvailable(torneo.getDescripcion())) {
			message = of("Ya existe un Torneo con esa descripción");
		}
		if (torneo.getEquipos() == null) {
			message = of("El Torneo debe tener al menos un Equipo principal");
		}
		if (torneo.getEquipos() != null
				&& torneo.getEquipos().get(0).getJugadores().size() != amountJugadoresByEquipo) {
			message = of("El equipo no cuenta con la cantidad de jugadores predeterminada");
		}

		if (message.isPresent()) {
			throw new BalonpieException(message.get());
		}
	}

	private void setInitialDataToTorneo(Torneo torneo) {
		torneo.setEstado(ACTIVO.getEstado());

		Calendar calendar = Calendar.getInstance();
		torneo.setFechaCreacion(calendar.getTime());
		torneo.setFechaInicio(torneo.getFechaCreacion());
		calendar.add(Calendar.DATE, 30);
		torneo.setFechaFin(calendar.getTime());
	}

	private void setInitialDataToEquipo(Torneo torneo) {
		Optional<Equipo> equipoOptional = torneo.getMainEquipo();

		if (equipoOptional.isPresent()) {
			Equipo equipo = equipoOptional.get();
			equipo.setPresupuesto(amountInitialBudget);
			equipo.setPuntos(amountInitialPoints);
			equipo.setFechaCreacion(torneo.getFechaCreacion());
			equipo.setEstado(ACTIVO.getEstado());
		}
	}
}
