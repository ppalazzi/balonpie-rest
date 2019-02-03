package com.palazzisoft.balonpie.service.factory;

import static com.google.common.collect.Lists.newArrayList;
import static com.palazzisoft.balonpie.service.model.Torneo.CANT_MAX;

import java.util.List;

import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.Fecha;
import com.palazzisoft.balonpie.service.model.Fixture;
import com.palazzisoft.balonpie.service.model.Partido;

public class FixtureGenerator {

    public Fixture crearFixture(List<Equipo> equipos) {
        validarDatosIngresador(equipos);

        Fixture fixture = new Fixture();

        int totalRounds = equipos.size() - 1;
        int matchesPerRound = equipos.size() / 2;

        for (int i = 0 ; i < totalRounds ; i++) {
            Fecha fecha = new Fecha();
            fecha.setNumero(i + 1);
            fecha.setFixture(fixture);

            for (int j = 0 ; j < matchesPerRound ; j++) {

                int local = (i + j) % (CANT_MAX - 1);
                int visitante = (CANT_MAX - 1 - j + i) % (CANT_MAX - 1);

                if (j == 0) {
                    visitante = CANT_MAX - 1;
                }

                Partido partido = new Partido();
                partido.setLocal(equipos.get(local));
                partido.setVisitante(equipos.get(visitante));
                partido.setFecha(fecha);
                fecha.agregarPartido(partido);
            }

            fixture.agregarFecha(fecha);
        }

        fixture.setFechas(intercambiar(fixture.getFechas()));
        intercalarElUltimoEquipoEntreLocalYVisitante(fixture.getFechas());

        return fixture;
    }

    private List<Fecha> intercambiar(List<Fecha> fechas) {
        List<Fecha> fechasIntercambiadas = newArrayList();

        int pares = 0;
        int impares = (CANT_MAX / 2);

        for (int i = 0; i < fechas.size(); i++) {
            if (i % 2 == 0) {
                fechasIntercambiadas.add(fechas.get(pares++));
            } else {
                fechasIntercambiadas.add(fechas.get(impares++));
            }
        }

        return fechasIntercambiadas;
    }

    private void intercalarElUltimoEquipoEntreLocalYVisitante(List<Fecha> fechas) {
        for (int i = 0; i < fechas.size() ; i++) {
            // si son impares intercambiar el vistante por el local para evitar que el ultimo siempre
            // sea visitante
            if (i % 2 == 1) {
                Partido partido = fechas.get(i).getPartidos().get(0);
                Partido nuevoPartido = new Partido();
                nuevoPartido.setFecha(partido.getFecha());
                nuevoPartido.setVisitante(partido.getLocal());
                nuevoPartido.setLocal(partido.getVisitante());

                fechas.get(i).getPartidos().set(0, nuevoPartido);
            }
        }
    }

    private static void validarDatosIngresador(final List<Equipo> equipos) {
        if (equipos.size() % 2 != 0) {
            throw new IllegalArgumentException("Los equipos deben ser pares");
        }
    }
}
