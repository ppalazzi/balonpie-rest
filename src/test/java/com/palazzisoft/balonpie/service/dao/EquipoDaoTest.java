package com.palazzisoft.balonpie.service.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.palazzisoft.balonpie.service.config.DatabaseConfig;
import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.enumeration.EEstado;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseConfig.class, RestConfig.class })
@WebAppConfiguration
@Transactional
public class EquipoDaoTest {

	@Autowired
	private EquipoDao equipoDao;
	
	@Test
	public void crearEquipo() {
		Equipo equipo = new Equipo();
		equipo.setNombre("nombre");
		equipo.setDescripcion("descripcion");
		equipo.setEstado(EEstado.ACTIVO.getEstado());
		
		equipoDao.saveEquipo(equipo);
		
		List<Equipo> equipos = equipoDao.getAvailableEquipos();
		
		assertNotNull(equipos);
		assertEquals(equipo.getNombre(), equipos.get(0).getNombre());
	}
}
