package com.palazzisoft.balonpie.service.factory;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.shuffle;

import java.util.List;

import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.Fecha;
import com.palazzisoft.balonpie.service.model.Fixture;
import com.palazzisoft.balonpie.service.model.Partido;

/**
 * Al pasarle una lista de Equipos, genera un Fixture con los pa
 * 
 * @author ppalazzi
 *
 */
@Deprecated
public class FixtureFactory {

	public static Fixture crearFixture(final List<Equipo> equipos) {
		validarDatosIngresador(equipos);

		Fixture fixture = new Fixture();

		int fechas = equipos.size() - 1;
		int partidosPorFecha = equipos.size() / 2;

		List<Equipo> primeraLista = equipos.subList(0, partidosPorFecha);
		List<Equipo> segundaLista = equipos.subList(partidosPorFecha, fechas + 1);

		// desordenamos las listas
		shuffle(primeraLista);
		shuffle(segundaLista);

		Equipo[] primerEquipoA = new Equipo[primeraLista.size()];
		primerEquipoA = primeraLista.toArray(primerEquipoA);

		Equipo[] segundoEquipoA = new Equipo[segundaLista.size()];
		segundoEquipoA = segundaLista.toArray(segundoEquipoA);

		for (int i = 0; i < fechas; i++) {
			Fecha fecha = new Fecha();
			fecha.setNumero(i + 1);
			fecha.setFixture(fixture);

			for (int j = 0; j < primeraLista.size(); j++) {
				Partido partido = new Partido();
				partido.setFecha(fecha);
				partido.setLocal(primerEquipoA[j]);
				partido.setVisitante(segundoEquipoA[j]);
				fecha.agregarPartido(partido);
			}

			List<Equipo> ordenesCambiados = intercambiarPosiciones(primerEquipoA, segundoEquipoA);
			primerEquipoA = ordenesCambiados.subList(0, partidosPorFecha).toArray(primerEquipoA);
			segundoEquipoA = ordenesCambiados.subList(partidosPorFecha, fechas + 1).toArray(segundoEquipoA);

			fixture.agregarFecha(fecha);
		}

		return fixture;
	}

	private static List<Equipo> intercambiarPosiciones(Equipo[] primerArray, Equipo[] segundoArray) {
		List<Equipo> resultado = newArrayList();

		Equipo[] primeroTemp = new Equipo[primerArray.length];
		Equipo[] segundoTemp = new Equipo[segundoArray.length];

		primeroTemp[0] = primerArray[0];
		segundoTemp[0] = primerArray[1];

		for (int i = 1; i < primeroTemp.length - 1; i++) {
			primeroTemp[i] = primerArray[i + 1];
		}

		primeroTemp[primeroTemp.length - 1] = segundoArray[segundoTemp.length - 1];

		for (int i = 1; i < segundoTemp.length - 1; i++) {
			segundoTemp[i] = segundoArray[i + 1];
		}

		segundoTemp[segundoTemp.length - 1] = segundoArray[segundoTemp.length - 2];

		resultado.addAll(newArrayList(primeroTemp));
		resultado.addAll(newArrayList(segundoTemp));

		return resultado;
	}

	private static void validarDatosIngresador(final List<Equipo> equipos) {
		if (equipos.size() % 2 != 0) {
			throw new IllegalArgumentException("Los equipos deben ser pares");
		}
	}
}
