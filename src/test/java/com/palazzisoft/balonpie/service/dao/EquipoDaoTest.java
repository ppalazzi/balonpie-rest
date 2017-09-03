package com.palazzisoft.balonpie.service.dao;

import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.palazzisoft.balonpie.service.config.DatabaseConfig;
import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.EquipoJugador;
import com.palazzisoft.balonpie.service.model.Jugador;
import com.palazzisoft.balonpie.service.model.enumeration.EEstado;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseConfig.class, RestConfig.class })
@WebAppConfiguration
@Transactional
public class EquipoDaoTest {

	@Autowired
	private EquipoDao equipoDao;
	
	@Autowired
	private JugadorDao jugadorDao;
	
	private Jugador jugador;
	
	@Before
	public void setup() {
        jugador = new Jugador();
        jugador.setNombre("carlos");
        jugadorDao.crearJugador(jugador);
	}
	
	@Test
	public void crearEquipo() {
		Equipo equipo = new Equipo();
		equipo.setNombre("nombre");
		equipo.setDescripcion("descripcion");
		equipo.setEstado(EEstado.ACTIVO.getEstado());
		equipo.setEquipoJugadores(buildJugador(equipo));
		
		equipoDao.saveEquipo(equipo);
		
		List<Equipo> equipos = equipoDao.getAvailableEquipos();
		
		assertNotNull(equipos);
		assertNotNull(equipos.get(0).getEquipoJugadores());
		assertEquals(equipo.getNombre(), equipos.get(0).getNombre());
		assertEquals(1, equipos.get(0).getEquipoJugadores().size());
	}
	
    private Set<EquipoJugador> buildJugador(Equipo equipo) {
        EquipoJugador ej = new EquipoJugador();
        ej.setJugador(jugador);
        ej.setEquipo(equipo);
        
        return newHashSet(ej);
    }
}
