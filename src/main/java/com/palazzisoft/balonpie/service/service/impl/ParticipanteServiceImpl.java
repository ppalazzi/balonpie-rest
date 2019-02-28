package com.palazzisoft.balonpie.service.service.impl;

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
import com.palazzisoft.balonpie.service.service.ParticipanteService;

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

	@Override
	@Transactional(readOnly = false)
	public ParticipanteDto actualizarParticipante(ParticipanteDto participanteDto) throws BalonpieException {
		LOG.info("Actualizando Participante");

		if (getParticipantePorEmail(participanteDto.getEmail()).isPresent()) {
			LOG.error("Email ya Registrado");
			throw new BalonpieException("Email ya Registrado");
		}

		Participante participante = participanteDao.findById(participanteDto.getId());
		if (participante == null) {
			LOG.error("Error al actualizar el Participante");
			throw new BalonpieException("Error al actualizar el Participante");
		}
		
		mapper.map(participanteDto, participante);		
		participanteDao.updateParticipante(participante);
		
		return mapper.map(participante, ParticipanteDto.class);
	}

	@Override
    @Transactional
    public Optional<ParticipanteDto> getParticipantePorEmail(String email) throws BalonpieException {
		LOG.info("Trayendo Participante por email " + email);

		Optional<Participante> participante = participanteDao.findParticipanteByEmail(email);
		if (participante.isPresent()) {
			return Optional.ofNullable(mapper.map(participante.get(), ParticipanteDto.class));
		}

		LOG.error("Error al traer el Participante por email");
		return Optional.empty();
	}
}
