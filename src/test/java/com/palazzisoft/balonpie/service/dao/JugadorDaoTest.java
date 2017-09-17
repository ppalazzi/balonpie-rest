package com.palazzisoft.balonpie.service.dao;

import static com.palazzisoft.balonpie.service.model.enumeration.EEstado.ACTIVO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.palazzisoft.balonpie.service.config.DatabaseConfig;
import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.model.Jugador;
import com.palazzisoft.balonpie.service.model.TipoJugador;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseConfig.class, RestConfig.class })
@WebAppConfiguration
@Transactional
public class JugadorDaoTest {

	@Autowired
	private JugadorDao jugadorDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Before
	public void setup() {
		session = sessionFactory.getCurrentSession();
		
		TipoJugador tipoJugador = new TipoJugador();
		tipoJugador.setId(0);
		tipoJugador.setDescripcion("Arquero");
		
		session.persist(tipoJugador);
	}
	
	@After
	public void tearDown() {
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testGetJugadoresByType() {		
		TipoJugador tipoJugador = new TipoJugador();
		tipoJugador.setId(0);
		
		Jugador jugador = new Jugador();
		jugador.setNombre("Pablo");
		jugador.setEstado(ACTIVO.getEstado());
		jugador.setTipoJugador(tipoJugador);
		
		jugadorDao.crearJugador(jugador);
		
		List<Jugador> arqueros = jugadorDao.getJugadoresByType(0);
		List<Jugador> defensas = jugadorDao.getJugadoresByType(1);
		
		assertNotNull(arqueros);
		assertTrue(defensas.isEmpty());
		assertEquals(jugador.getNombre(), arqueros.get(0).getNombre());
	}
}
