package com.palazzisoft.balonpie.service.service.impl;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.model.Partido;
import com.palazzisoft.balonpie.service.service.PartidoService;

@Service("partidoService")
@Transactional
public class PartidoServiceImpl implements PartidoService {

    @Override
    public void jugarPartido(Partido partido) {
        int sumaDelanterosLocal = partido.getLocal().getEquipoJugadores().stream()
                .filter(j -> j.getJugador().getTipoJugador().getId() == 3).mapToInt(j -> j.getJugador().getVelocidad())
                .sum() / 3;

        int sumaDefensoresVisitante = partido.getVisitante().getEquipoJugadores().stream()
                .filter(j -> j.getJugador().getTipoJugador().getId() == 1).mapToInt(j -> j.getJugador().getFisico())
                .sum() / 3;

        int sumaDelanterosVisitante = partido.getVisitante().getEquipoJugadores().stream()
                .filter(j -> j.getJugador().getTipoJugador().getId() == 3).mapToInt(j -> j.getJugador().getHabilidad())
                .sum() / 3;

        int sumaDefensoresLocal = partido.getLocal().getEquipoJugadores().stream()
                .filter(j -> j.getJugador().getTipoJugador().getId() == 1).mapToInt(j -> j.getJugador().getFisico())
                .sum() /3;

        Random rand = new Random();
        if ( (sumaDelanterosLocal - sumaDefensoresVisitante) < 0) {
            int golesLocal = rand.nextInt((1 - 0) + 1) + 0;

            if (rand.nextInt() % 2 == 0) {
                // gol adicional por localia randomico
                golesLocal++;
            }

            partido.setGolesLocal(golesLocal);
        }
        else if ( (sumaDelanterosLocal - sumaDefensoresVisitante) > 0) {
            // r.nextInt((max - min) + 1) + min;

            int diferencia = sumaDelanterosLocal - sumaDefensoresVisitante;

            int golesLocal = rand.nextInt( (diferencia - 0) + 1) + 0;
            partido.setGolesLocal(golesLocal);
        }
        else {
            partido.setGolesLocal(rand.nextInt(5));
        }


        if ( (sumaDelanterosVisitante - sumaDefensoresLocal) < 0) {
            int golesVisitante = rand.nextInt((1 - 0) + 1) + 0;

            if (rand.nextInt() % 2 == 0) {
                // gol adicional por localia randomico
                golesVisitante++;
            }

            partido.setGolesVisitante(golesVisitante);
        }
        else if ( (sumaDefensoresVisitante - sumaDefensoresLocal) > 0) {
            int diferencia = sumaDefensoresVisitante - sumaDefensoresLocal;

            int golesVisitante = rand.nextInt( (diferencia - 0) + 1) + 0;
            partido.setGolesVisitante(golesVisitante);
        }
        else {
            partido.setGolesVisitante(rand.nextInt(5));
        }

        partido.setJugado(true);
    }

}
