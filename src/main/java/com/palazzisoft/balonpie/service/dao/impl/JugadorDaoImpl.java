package com.palazzisoft.balonpie.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.JugadorDao;
import com.palazzisoft.balonpie.service.model.Jugador;
@Repository("jugadorDao")
public class JugadorDaoImpl extends AbstractDao implements JugadorDao {

    @Override
    public void crearJugador(Jugador jugador) {
        this.persist(jugador);
    }

}
