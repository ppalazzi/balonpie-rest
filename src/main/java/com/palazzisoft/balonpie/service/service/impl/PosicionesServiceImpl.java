package com.palazzisoft.balonpie.service.service.impl;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Comparator.comparing;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.dto.EquiposEstadisticasDto;
import com.palazzisoft.balonpie.service.dto.FechaDto;
import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.dto.PartidoDto;
import com.palazzisoft.balonpie.service.model.Fixture;
import com.palazzisoft.balonpie.service.service.PosicionesService;

@Service("posicionesService")
@Transactional
public class PosicionesServiceImpl implements PosicionesService {

	private Map<EquipoDto, EquiposEstadisticasDto> mapsEquiposPuntos;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<EquiposEstadisticasDto> getPosicionesByFixture(Fixture fixture) {
        mapsEquiposPuntos = new TreeMap<>();

	    FixtureDto fixtureDto = mapper.map(fixture, FixtureDto.class);

		for (FechaDto fechas : fixtureDto.getFechas()) {
			for (PartidoDto partido : fechas.getPartidos()) {
				if (partido.isJugado()) {
					addPointsToMap(partido);
				}
			}
		}

		List<EquiposEstadisticasDto> estadisticas = newArrayList(mapsEquiposPuntos.values());		
		
		estadisticas.sort(comparing(EquiposEstadisticasDto::getPuntos)
				.thenComparing(EquiposEstadisticasDto::getGolesAFavor).reversed());

		return estadisticas;
	}

	private synchronized void addPointsToMap(PartidoDto partido) {
		if (partido.getGolesLocal() > partido.getGolesVisitante()) {
			EquiposEstadisticasDto estadisticasLocal = getEquipoFromSource(partido.getLocal());
			estadisticasLocal.agregarGolesAFavor(partido.getGolesLocal());
			estadisticasLocal.agregarGolesRecibidos(partido.getGolesVisitante());
			estadisticasLocal.agregarVictoria();
			mapsEquiposPuntos.put(partido.getLocal(), estadisticasLocal);

			EquiposEstadisticasDto estadisticasVisitante = getEquipoFromSource(partido.getVisitante());
			estadisticasVisitante.agregarGolesAFavor(partido.getGolesVisitante());
			estadisticasVisitante.agregarGolesRecibidos(partido.getGolesLocal());
			mapsEquiposPuntos.put(partido.getVisitante(), estadisticasVisitante);
		}

		if (partido.getGolesLocal() < partido.getGolesVisitante()) {
			EquiposEstadisticasDto estadisticasVisitante = getEquipoFromSource(partido.getVisitante());
			estadisticasVisitante.setGolesAFavor(partido.getGolesVisitante());
			estadisticasVisitante.setGolesRecibidos(partido.getGolesLocal());
			estadisticasVisitante.agregarVictoria();
			mapsEquiposPuntos.put(partido.getVisitante(), estadisticasVisitante);

			EquiposEstadisticasDto estadisticasLocal = getEquipoFromSource(partido.getLocal());
			estadisticasLocal.agregarGolesAFavor(partido.getGolesLocal());
			estadisticasLocal.agregarGolesRecibidos(partido.getGolesVisitante());
			mapsEquiposPuntos.put(partido.getLocal(), estadisticasLocal);
		}

		if (partido.getGolesLocal() == partido.getGolesVisitante()) {
			EquiposEstadisticasDto estadisticasLocal = getEquipoFromSource(partido.getLocal());
			EquiposEstadisticasDto estadisticasVisitante = getEquipoFromSource(partido.getVisitante());

			estadisticasLocal.agregarEmpate();
			estadisticasVisitante.agregarEmpate();

			mapsEquiposPuntos.put(partido.getVisitante(), estadisticasVisitante);
			mapsEquiposPuntos.put(partido.getLocal(), estadisticasLocal);
		}
	}

	private EquiposEstadisticasDto getEquipoFromSource(EquipoDto equipoDto) {
		if (mapsEquiposPuntos.containsKey(equipoDto)) {
			return mapsEquiposPuntos.get(equipoDto);
		}

		EquiposEstadisticasDto estadisticas = new EquiposEstadisticasDto(equipoDto);
		mapsEquiposPuntos.put(equipoDto, estadisticas);

		return estadisticas;
	}

}
