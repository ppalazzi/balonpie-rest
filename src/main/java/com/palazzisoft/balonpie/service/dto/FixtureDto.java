package com.palazzisoft.balonpie.service.dto;

import java.util.List;

public class FixtureDto {

	private Integer id;
	private List<FechaDto> fechas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<FechaDto> getFechas() {
		return fechas;
	}

	public void setFechas(List<FechaDto> fechas) {
		this.fechas = fechas;
	}

}
