package com.palazzisoft.balonpie.service.dao;

import java.util.List;

import com.palazzisoft.balonpie.service.dto.EquipoDto;
import com.palazzisoft.balonpie.service.model.Equipo;

public interface EquipoDao {

	void saveEquipo(Equipo equipo);

	List<Equipo> getAvailableEquipos();

	Equipo findById(Integer id);
}
