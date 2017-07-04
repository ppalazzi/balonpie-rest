package com.palazzisoft.balonpie.service.dao;

import java.util.List;

import com.palazzisoft.balonpie.service.model.Torneo;

/**
 * 
 * @author ppalazzi
 *
 */
public interface TorneoDao {

	List<Torneo> obtenerTorneosDeParticipante(Integer participanteId);

	void crearTorneo(Torneo torneo);

}
