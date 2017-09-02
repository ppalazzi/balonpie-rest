package com.palazzisoft.balonpie.service.service.impl;

import static com.palazzisoft.balonpie.service.model.enumeration.EEstado.ACTIVO;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.JugadorDao;
import com.palazzisoft.balonpie.service.dto.JugadorDto;
import com.palazzisoft.balonpie.service.model.Jugador;
import com.palazzisoft.balonpie.service.service.JugadorService;

@Service("jugadorService")
@Transactional
public class JugadorServiceImpl implements JugadorService {

    private Logger LOG = LoggerFactory.getLogger(JugadorServiceImpl.class);

    @Autowired
    private JugadorDao jugadorDao;

    @Autowired
    private ModelMapper mapper;

    @Override
    public JugadorDto saveJugador(JugadorDto jugador) {
        LOG.info("Guardando jugador con Nombre {} y Apellido {}", jugador.getNombre(), jugador.getApellido());

        Jugador jugadorEntity = mapper.map(jugador, Jugador.class);
        jugadorEntity.setEstado(ACTIVO.getEstado());
        jugadorDao.crearJugador(jugadorEntity);
        
        return mapper.map(jugadorEntity, JugadorDto.class);
    }

	@Override
	public List<Jugador> getJugadorByType(Integer type) {
		LOG.info("Trayendo todos los jugadores del tipo {}", type);
		return jugadorDao.getJugadoresByType(type);
	}

	@Override
	public JugadorDto getJugadoresById(Integer id) {
		Jugador jugador = this.jugadorDao.findById(id);
		return mapper.map(jugador, JugadorDto.class);
	}
}
