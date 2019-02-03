package com.palazzisoft.balonpie.service.service.factory;

import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.palazzisoft.balonpie.service.factory.FixtureGenerator;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.model.Fixture;

@RunWith(SpringJUnit4ClassRunner.class)
public class FixtureGeneratorTest {

    private  FixtureGenerator fixtureGenerator;

    @Test
    public void testFixture() {
        fixtureGenerator = new FixtureGenerator();

        Fixture fixture = fixtureGenerator.crearFixture(buildEquipos());

        assertNotNull(fixture);
        assertTrue(fixture.getFechas().size() == 9);
    }

    private List<Equipo> buildEquipos() {
        List<Equipo> equipos = newArrayList();

        Equipo equipo1 = new Equipo();
        equipo1.setId(1);

        Equipo equipo2 = new Equipo();
        equipo2.setId(2);

        Equipo equipo3 = new Equipo();
        equipo3.setId(3);

        Equipo equipo4 = new Equipo();
        equipo4.setId(4);

        Equipo equipo5 = new Equipo();
        equipo5.setId(5);

        Equipo equipo6 = new Equipo();
        equipo6.setId(6);

        Equipo equipo7 = new Equipo();
        equipo7.setId(7);

        Equipo equipo8 = new Equipo();
        equipo8.setId(8);

        Equipo equipo9 = new Equipo();
        equipo9.setId(9);

        Equipo equipo10 = new Equipo();
        equipo10.setId(10);

        return newArrayList(equipo1, equipo2, equipo3, equipo4, equipo5, equipo6, equipo7, equipo8, equipo9,
                equipo10);
    }
}
