package com.palazzisoft.balonpie.service.service;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.dto.FechaDto;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.Fecha;
import com.palazzisoft.balonpie.service.model.Partido;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestConfig.class })
@WebAppConfiguration
public class FechaServiceTest {

	@Autowired
	private FechaService fechaService;

	@Autowired
	private EquipoService equipoService;

	private Fecha fecha;

	@Before
	public void setup() {
		fecha = new Fecha();
		fecha.setNumero(1);

		Equipo local = new Equipo();
		local.setDescripcion("local");
		equipoService.saveEquipo(local);

		Equipo visitante = new Equipo();
		visitante.setDescripcion("visitante");
		equipoService.saveEquipo(visitante);

		Partido partido = new Partido();
		partido.setLocal(local);
		partido.setVisitante(visitante);
		partido.setFecha(fecha);

		fecha.agregarPartido(partido);
		fechaService.saveFecha(fecha);
	}

	@Test
	public void jugarFechaTest() {
		FechaDto fechaDto = new FechaDto();
		fechaDto.setId(fecha.getId());
		fechaDto.setNumero(1);

		FechaDto actual = fechaService.jugarFecha(fechaDto);

		assertEquals(actual.getNumero(), fechaDto.getNumero());
		assertEquals(actual.getPartidos().size(), 1);
		assertPartidos(actual);
	}
	
	private void assertPartidos(FechaDto fecha) {
		fecha.getPartidos().stream().forEach(p -> {
			Assert.assertTrue(p.isJugado());
		});
	}
}
