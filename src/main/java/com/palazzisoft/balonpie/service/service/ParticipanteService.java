package com.palazzisoft.balonpie.service.service;

import java.util.Optional;

import com.palazzisoft.balonpie.service.dto.ParticipanteDto;
import com.palazzisoft.balonpie.service.exception.BalonpieException;
import com.palazzisoft.balonpie.service.model.Participante;

public interface ParticipanteService {

    ParticipanteDto saveParticipante(ParticipanteDto participante) throws BalonpieException;

    Participante retrieveParticipante(Integer id);

    Optional<ParticipanteDto> getParticipanteByCredentials(String user, String password);
    
    ParticipanteDto actualizarParticipante(ParticipanteDto participanteDto) throws BalonpieException;
}
