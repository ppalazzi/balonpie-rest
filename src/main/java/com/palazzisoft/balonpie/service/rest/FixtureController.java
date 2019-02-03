package com.palazzisoft.balonpie.service.rest;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.palazzisoft.balonpie.service.dao.FixtureDao;
import com.palazzisoft.balonpie.service.dto.EquiposEstadisticasDto;
import com.palazzisoft.balonpie.service.dto.FixtureDto;
import com.palazzisoft.balonpie.service.model.Fixture;
import com.palazzisoft.balonpie.service.service.PosicionesService;

@RestController
public class FixtureController {

    private Logger LOG = LoggerFactory.getLogger(FixtureController.class);

    @Autowired
    private PosicionesService posicionesService;

    @Autowired
    private FixtureDao fixtureDao;

    @RequestMapping(value = "/fixture/posiciones/{torneoId}", method = GET)
    public ResponseEntity<List<EquiposEstadisticasDto>> posiciones(final @PathVariable Integer torneoId) {
        LOG.info("Calculando estadisticas para el Torneo " + torneoId);

        try {
            Optional<Fixture> fixtureOptional = fixtureDao.getFixtureByTorneo(torneoId);

            if (fixtureOptional.isPresent()) {
                List<EquiposEstadisticasDto> estadisticas =
                        posicionesService.getPosicionesByFixture(fixtureOptional.get());
                return new ResponseEntity<>(estadisticas, HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Fixture no existe en base");
            }
        } catch (Exception e) {
            LOG.error("Ocurrio un problema al jugar la fecha", e);
            return new ResponseEntity<>(NOT_ACCEPTABLE);
        }
    }
}
