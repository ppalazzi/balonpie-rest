package com.palazzisoft.balonpie.service.rest;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.EquipoJugador;
import com.palazzisoft.balonpie.service.model.Jugador;
import com.palazzisoft.balonpie.service.model.enumeration.EEstado;
import com.palazzisoft.balonpie.service.service.EquipoService;
import com.palazzisoft.balonpie.service.service.JugadorService;

@RestController
public class CreateEquipoAutomaticProcess {

	private Logger LOG = getLogger(CreateEquipoAutomaticProcess.class);

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private JugadorService jugadorService;

	@Value("${equipo.presupuesto.inicial}")
	private Long amountInitialBudget;

	@Value("${equipo.puntos.inicial}")
	private Long amountInitialPoints;

	@RequestMapping(value = "/equipo/crearEquipos", method = GET)
	public void execute(@RequestParam String equiposNombres) {
		String[] equipos = equiposNombres.split(",");

		Stream<String> streams = stream(equipos);
		streams.forEach(s -> {
			Equipo equipo = new Equipo();
			equipo.setNombre(s);
			equipo.setEstado(EEstado.ACTIVO.getEstado());
			equipo.setFechaCreacion(Calendar.getInstance().getTime());
			equipo.setPresupuesto(amountInitialBudget);
			equipo.setPuntos(amountInitialPoints);

			buildJugadoresFromTeam(equipo);

			LOG.info("Guardando equipo con nombre {}", s);

			equipoService.saveEquipo(equipo);
		});
	}

	private void buildJugadoresFromTeam(Equipo equipo) {
		Set<EquipoJugador> arqueros = getJugadoresByTypeAndAmount(0, 1, equipo);
		Set<EquipoJugador> defensas = getJugadoresByTypeAndAmount(1, 4, equipo);
		Set<EquipoJugador> volantes = getJugadoresByTypeAndAmount(2, 3, equipo);
		Set<EquipoJugador> ataque = getJugadoresByTypeAndAmount(3, 3, equipo);

		Set<EquipoJugador> todos = new HashSet<>();
		todos.addAll(arqueros);
		todos.addAll(defensas);
		todos.addAll(volantes);
		todos.addAll(ataque);

		equipo.setEquipoJugadores(todos);
	}

	private Set<EquipoJugador> getJugadoresByTypeAndAmount(Integer type, Integer amount, Equipo equipo) {
		List<Jugador> jugadores = jugadorService.getJugadorByType(type);
		return jugadores.stream().limit(amount).map(j -> createEquipoJugador(equipo, j)).collect(toSet());
	}

	private EquipoJugador createEquipoJugador(Equipo equipo, Jugador jugador) {
		EquipoJugador equipoJugador = new EquipoJugador();
		equipoJugador.setEquipo(equipo);
		equipoJugador.setJugador(jugador);

		return equipoJugador;
	}
}
