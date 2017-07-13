package com.palazzisoft.balonpie.service.dao;

import java.util.List;

import com.palazzisoft.balonpie.service.model.Torneo;

/**
 * 
 * @author ppalazzi
 *
 */
public interface TorneoDao {

	List<Torneo> getTorneosByParticipante(Integer participanteId);

	void saveTorneo(Torneo torneo);

    boolean isDescripcionAvailable(String descripcion);

}
