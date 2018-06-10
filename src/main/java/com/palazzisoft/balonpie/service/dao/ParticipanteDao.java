package com.palazzisoft.balonpie.service.dao;

import java.util.Optional;

import com.palazzisoft.balonpie.service.model.Participante;

public interface ParticipanteDao {

	Participante findById(Integer id);

	Optional<Participante> findParticipanteByCredentials(final String user, final String pass);

	Optional<Participante> findParticipanteByEmail(String email);
	
	void saveParticipante(Participante participante);
	
	void updateParticipante(Participante participante);
}
