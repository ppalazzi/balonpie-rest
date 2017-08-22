package com.palazzisoft.balonpie.service.service.impl;

import static com.palazzisoft.balonpie.service.model.enumeration.EEstado.ACTIVO;
import static java.util.Optional.of;
import static jersey.repackaged.com.google.common.collect.Lists.newArrayList;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.EquipoDao;
import com.palazzisoft.balonpie.service.dao.ParticipanteDao;
import com.palazzisoft.balonpie.service.dao.TorneoDao;
import com.palazzisoft.balonpie.service.dto.TorneoDto;
import com.palazzisoft.balonpie.service.exception.BalonpieException;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.Torneo;
import com.palazzisoft.balonpie.service.model.enumeration.EEstado;
import com.palazzisoft.balonpie.service.service.TorneoService;

@Service("torneoService")
@Transactional
public class TorneoServiceImpl implements TorneoService {

    private Logger LOG = LoggerFactory.getLogger(TorneoServiceImpl.class);

    @Autowired
    private TorneoDao torneoDao;

    @Autowired
    private EquipoDao equipoDao;

    @Autowired
    private ParticipanteDao participanteDao;

    @Autowired
    private ModelMapper mapper;

    @Value("${cantidad.jugadores.equipos}")
    private int amountJugadoresByEquipo;

    @Value("${equipo.presupuesto.inicial}")
    private Long amountInitialBudget;

    @Value("${equipo.puntos.inicial}")
    private Long amountInitialPoints;

    @Override
    public List<TorneoDto> getTorneosByParticipante(Integer participanteId) {
        LOG.info("Obteniendo torneos de participante {}", participanteId);

        List<Torneo> torneos = torneoDao.getTorneosByParticipante(participanteId);
        List<TorneoDto> torneosDto = newArrayList();
        mapper.map(torneos, torneosDto);
        return torneosDto;
    }

    @Override
    public TorneoDto createTorneo(TorneoDto torneoDto) throws BalonpieException {
        validateNewTorneo(torneoDto);

        LOG.info("Guardando nuevo Torneo {} ", torneoDto);

        Torneo torneo = mapper.map(torneoDto, Torneo.class);
        setInitialDataToTorneo(torneo);
        setInitialDataToEquipo(torneo);
        // TODO Crear los equipos rivales con sus jugadores randomizados

        equipoDao.saveEquipo(torneo.getEquipos().get(0));
        torneoDao.saveTorneo(torneo);

        return mapper.map(torneo, TorneoDto.class);
    }

    private void validateNewTorneo(TorneoDto torneo) throws BalonpieException {
        Optional<String> message = Optional.empty();

        if (torneoDao.isDescripcionAvailable(torneo.getDescripcion())) {
            message = of("Ya existe un Torneo con esa descripción");
        }
        if (torneo.getEquipos() == null) {
            message = of("El Torneo debe tener al menos un Equipo principal");
        }
        if (torneo.getEquipos() != null && torneo.getEquipos().get(0).getJugadores().size() != amountJugadoresByEquipo) {
            message = of("El equipo no cuenta con la cantidad de jugadores predeterminada");
        }

        if (message.isPresent()) {
            throw new BalonpieException(message.get());
        }
    }

    private void setInitialDataToTorneo(Torneo torneo) {
        torneo.setEstado(ACTIVO.getEstado());

        Calendar calendar = Calendar.getInstance();
        torneo.setFechaCreacion(calendar.getTime());
        torneo.setFechaInicio(torneo.getFechaCreacion());
        calendar.add(Calendar.DATE, 30);
        torneo.setFechaFin(calendar.getTime());
    }

    private void setInitialDataToEquipo(Torneo torneo) {
        Optional<Equipo> equipoOptional = torneo.getMainEquipo();

        if (equipoOptional.isPresent()) {
            Equipo equipo = equipoOptional.get();
            equipo.setPresupuesto(amountInitialBudget);
            equipo.setPuntos(amountInitialPoints);
            equipo.setFechaCreacion(torneo.getFechaCreacion());
            equipo.setEstado(ACTIVO.getEstado());
        }
    }
     
}
