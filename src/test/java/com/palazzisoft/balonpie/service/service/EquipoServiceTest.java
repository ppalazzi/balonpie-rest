package com.palazzisoft.balonpie.service.service;

import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.dto.JugadorDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestConfig.class })
@WebAppConfiguration
public class EquipoServiceTest {

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private JugadorService jugadorService;

	private JugadorDto jugadorDto;
	
	@Before
	public void setup() {
		JugadorDto dto = new JugadorDto();
		dto.setNombre("carlos");
		dto.setTipoJugador(0);
		jugadorDto = jugadorService.saveJugador(dto);
	}

	@Test
	public void equipoShouldHaveJugadores() {
		EquipoDto equipo = new EquipoDto();
		equipo.setNombre("nombre");
		equipo.setJugadores(newHashSet(jugadorDto));

		equipoService.saveEquipo(equipo);
		
		assertEquals(equipo.getJugadores(), 1);
	}
}
