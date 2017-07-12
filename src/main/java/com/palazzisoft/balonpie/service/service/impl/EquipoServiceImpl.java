package com.palazzisoft.balonpie.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palazzisoft.balonpie.service.dao.EquipoDao;
import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.model.Equipo;
import com.palazzisoft.balonpie.service.service.EquipoService;

@Service("equipoService")
@Transactional
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoDao equipoDao;

    @Override
    public void saveEquipo(Equipo equipo) {
        equipoDao.saveEquipo(equipo);
    }

}
