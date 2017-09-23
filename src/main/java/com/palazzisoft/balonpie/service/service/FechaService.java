package com.palazzisoft.balonpie.service.service;

import com.palazzisoft.balonpie.service.dto.FechaDto;
import com.palazzisoft.balonpie.service.model.Fecha;

public interface FechaService {

	FechaDto jugarFecha(FechaDto fechaDto);

	void saveFecha(Fecha fecha);
}
