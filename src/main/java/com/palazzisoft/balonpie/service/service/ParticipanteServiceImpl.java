package com.palazzisoft.balonpie.service.service;

import static com.palazzisoft.balonpie.service.model.enumeration.EEstado.ACTIVO;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.ParticipanteDao;
import com.palazzisoft.balonpie.service.dto.ParticipanteDto;
import com.palazzisoft.balonpie.service.exception.BalonpieException;
import com.palazzisoft.balonpie.service.model.Participante;

@Service("participanteService")
@Transactional
public class ParticipanteServiceImpl implements ParticipanteService {

	private Logger LOG = LoggerFactory.getLogger(ParticipanteServiceImpl.class);

	@Autowired
	private ParticipanteDao participanteDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional(readOnly = false)
	public ParticipanteDto saveParticipante(ParticipanteDto participanteDto) throws BalonpieException {
		LOG.info("Guardando Participante");

		Optional<Participante> participanteOptional = participanteDao
				.findParticipanteByEmail(participanteDto.getEmail());

		if (participanteOptional.isPresent()) {
			LOG.info("El email ya existe en sistema");
			throw new BalonpieException("El email ya existe en sistema");
		}

		Participante participante = mapper.map(participanteDto, Participante.class);
		participante.setEstado(ACTIVO.getEstado());
		participanteDao.saveParticipante(participante);		
		
		return mapper.map(participante, ParticipanteDto.class);
	}

	@Override
	public Participante retrieveParticipante(Integer id) {
		return this.participanteDao.findById(id);
	}

	@Override
	public Optional<ParticipanteDto> getParticipanteByCredentials(final String user, final String password) {
		Optional<Participante> participanteOptional = this.participanteDao.findParticipanteByCredentials(user,
				password);
		return participanteOptional.map(participante -> mapper.map(participante, ParticipanteDto.class));
	}
}
