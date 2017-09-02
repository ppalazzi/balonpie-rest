package com.palazzisoft.balonpie.service.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
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

	@Override
	public List<Jugador> getJugadoresByType(final Integer type) {
		Query<Jugador> query = getSession().createQuery(
				"FROM Jugador where tipoJugador.id = :tipoJugador and estado = 1 and size(equipoJugadores) = 0",
				Jugador.class);
		query.setParameter("tipoJugador", type);

		return query.getResultList();
	}

	@Override
	public Jugador findById(Integer id) {
		return this.getSession().find(Jugador.class, id);
	}

}
