package com.palazzisoft.balonpie.service.service;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.collect.Lists;
import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.dto.EquiposEstadisticasDto;
import com.palazzisoft.balonpie.service.dto.FechaDto;
import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.dto.PartidoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestConfig.class })
@WebAppConfiguration
public class PosicionesServiceTest {

	@Autowired
	private PosicionesService posicionesService;

	@Test
	public void testPosicionesByfixture() {
		List<EquiposEstadisticasDto> posiciones = this.posicionesService
				.getPosicionesByFixture(null);
		assertTrue(!posiciones.isEmpty());
	}

	private FixtureDto buildFixtureDto() {
		FixtureDto fixture = new FixtureDto();

		List<FechaDto> fechas = buildFechas();
		fixture.setFechas(fechas);

		return fixture;
	}

	private List<FechaDto> buildFechas() {
		List<FechaDto> fechas = Lists.newArrayList();

		FechaDto fechaDto = new FechaDto();
		List<PartidoDto> partidos = buildPartidos();
		fechaDto.setPartidos(partidos);
		fechas.add(fechaDto);

		return fechas;
	}

	private List<PartidoDto> buildPartidos() {
		List<PartidoDto> partidos = newArrayList();

		List<EquipoDto> equipos = buildEquipos();

		PartidoDto partido1 = new PartidoDto();
		partido1.setLocal(equipos.get(0));
		partido1.setVisitante(equipos.get(1));
		partido1.setGolesLocal(3);
		partido1.setGolesVisitante(1);
		partido1.setJugado(true);		
		partidos.add(partido1);

		PartidoDto partido2 = new PartidoDto();
		partido2.setLocal(equipos.get(2));
		partido2.setVisitante(equipos.get(3));
		partido2.setGolesLocal(1);
		partido2.setGolesVisitante(0);
		partido2.setJugado(true);
		partidos.add(partido2);

		return partidos;
	}

	private List<EquipoDto> buildEquipos() {
		List<EquipoDto> equipos = newArrayList();

		EquipoDto equipo1 = new EquipoDto(1);
		EquipoDto equipo2 = new EquipoDto(2);
		EquipoDto equipo3 = new EquipoDto(3);
		EquipoDto equipo4 = new EquipoDto(4);

		equipos.add(equipo1);
		equipos.add(equipo2);
		equipos.add(equipo3);
		equipos.add(equipo4);

		return equipos;
	}
}
