package com.palazzisoft.balonpie.service.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.palazzisoft.balonpie.service.config.RestConfig;
import com.palazzisoft.balonpie.service.dto.JugadorDto;
import com.palazzisoft.balonpie.service.model.enumeration.EEstado;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestConfig.class })
@WebAppConfiguration
public class JugadorServiceTest {

    @Autowired
    private JugadorService jugadorService;

    @Test
    public void createJugadorTest() {
        JugadorDto jugador = new JugadorDto();
        jugador.setApellido("Apellido");
        jugador.setFisico(4);
        jugador.setHabilidad(7);
        jugador.setNombre("Nombre");
        jugador.setRemate(0);
        jugador.setTipoJugador(0);
        jugador.setValor(444);
        jugador.setVelocidad(8);

        JugadorDto jugadorExpected = jugadorService.saveJugador(jugador);
        
        assertEquals(jugadorExpected.getApellido(),jugador.getApellido());
        Assert.assertTrue(jugadorExpected.getEstado() == EEstado.ACTIVO.getEstado());
        assertNotEquals(jugadorExpected, jugador);
    }
}
