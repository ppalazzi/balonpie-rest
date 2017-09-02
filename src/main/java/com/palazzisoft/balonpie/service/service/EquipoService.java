package com.palazzisoft.balonpie.service.service;

import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.model.Equipo;

public interface EquipoService {

    void saveEquipo(Equipo equipo);
    
    EquipoDto getEquipoById(Integer id);

    void saveEquipo(EquipoDto equipoDto);
}
