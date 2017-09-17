package com.palazzisoft.balonpie.service.dto;

import java.util.List;

public class FechaDto {

	private Integer id;
	private Integer numero;
	private List<PartidoDto> partidos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<PartidoDto> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<PartidoDto> partidos) {
		this.partidos = partidos;
	}

}
