package com.palazzisoft.balonpie.service.service;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.dto.JugadorDto;
import com.palazzisoft.balonpie.service.dto.ParticipanteDto;
import com.palazzisoft.balonpie.service.dto.TorneoDto;
import com.palazzisoft.balonpie.service.exception.BalonpieException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestConfig.class })
@WebAppConfiguration
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:convertcsv-new.sql")})

public class TorneoServiceTest {

    @Autowired
    private TorneoService torneoService;

    @Autowired
    private ParticipanteService participanteService;

    private ParticipanteDto participante;

    @Before
    public void setup() throws BalonpieException {
        this.participante = participanteService.saveParticipante(buildParticipanteDto());
    }

    @Test
    public void testCrearTorneoSucess() throws BalonpieException {
        TorneoDto torneo = new TorneoDto();
        torneo.setDescripcion("Torneo Nuevo");
        torneo.setParticipante(participante);

        EquipoDto equipo = new EquipoDto();
        equipo.setNombre("Nombre Equipo");
        equipo.setDescripcion("Descripcion Equipo");
        equipo.setParticipante(participante);
        equipo.setJugadores(newHashSet(buildJugadores()));
        torneo.setEquipos(newArrayList(equipo));

        TorneoDto torneoExpected = torneoService.createTorneo(torneo);

        // checking the torneo creation
        assertEquals(torneo.getDescripcion(), torneoExpected.getDescripcion());
        assertEquals(torneo.getParticipante().getId(), torneoExpected.getParticipante().getId());
        assertNotNull(torneoExpected.getEstado());
        assertNotNull(torneoExpected.getFechaCreacion());
        assertNotNull(torneoExpected.getFechaFin());
        assertNotNull(torneoExpected.getFechaInicio());

        // checking main equipo creation
        assertEquals(10, torneoExpected.getEquipos().size());
        EquipoDto equipoExpected = torneoExpected.getEquipos().get(0);
        assertEquals(torneo.getEquipos().get(0).getDescripcion(), equipoExpected.getDescripcion());
        assertNotNull(equipoExpected.getDescripcion());
        assertNotNull(equipoExpected.getEstado());
        assertNotNull(equipoExpected.getFechaCreacion());
        assertNotNull(equipoExpected.getPresupuesto());
        assertNotNull(equipoExpected.getPuntos());
        assertEquals(participante.getId(), equipoExpected.getParticipante().getId());
    }

    private ParticipanteDto buildParticipanteDto() {
        ParticipanteDto dto = new ParticipanteDto();
        dto.setNombre("Pablo");

        return dto;
    }
    
    private List<JugadorDto> buildJugadores() {
        List<JugadorDto> list = newArrayList();
        IntStream.range(0, 11).forEach(i -> list.add(new JugadorDto()));
        return list;
    }
}
