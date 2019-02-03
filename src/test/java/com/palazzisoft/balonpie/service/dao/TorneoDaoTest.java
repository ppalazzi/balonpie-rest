package com.palazzisoft.balonpie.service.dao;

import static com.palazzisoft.balonpie.service.model.enumeration.EEstado.ACTIVO;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.palazzisoft.balonpie.service.config.DatabaseConfig;
import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.model.Participante;
import com.palazzisoft.balonpie.service.model.Torneo;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { DatabaseConfig.class, RestConfig.class })
@WebAppConfiguration
@Transactional
public class TorneoDaoTest {

    @Autowired
    private TorneoDao torneoDao;
    
    @Autowired
    private ParticipanteDao participanteDao;

    @Test
    public void createTorneo() {
        Participante participante  = new Participante();
        this.participanteDao.saveParticipante(participante);
        
        Torneo torneo  = new Torneo();
        torneo.setDescripcion("Carlitos");
        torneo.setEstado(ACTIVO.getEstado());
        torneo.setParticipante(participante);
                
        torneoDao.saveTorneo(torneo);
        
        List<Torneo> torneos = torneoDao.getTorneosByParticipante(participante.getId());
        
        assertEquals(1, torneos.size());
        assertEquals(participante.getId(), torneos.get(0).getParticipante().getId());
    }
}
