package com.palazzisoft.balonpie.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.palazzisoft.balonpie.service.dao.AbstractDao;
import com.palazzisoft.balonpie.service.dao.EquipoDao;
import com.palazzisoft.balonpie.service.model.Equipo;

@Repository("equipoDao")
public class EquipoDaoImpl extends AbstractDao implements EquipoDao {

    @Override
    public void saveEquipo(Equipo equipo) {
        persist(equipo);
    }

}
