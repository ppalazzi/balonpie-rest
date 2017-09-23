package com.palazzisoft.balonpie.service.dao;

import com.palazzisoft.balonpie.service.model.Fecha;

public interface FechaDao {

	Fecha getFechaById(Integer id);

	void saveFecha(Fecha fecha);
}
