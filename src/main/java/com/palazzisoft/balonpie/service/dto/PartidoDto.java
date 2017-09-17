package com.palazzisoft.balonpie.service.dto;

public class PartidoDto {

	private Integer id;
	private EquipoDto local;
	private EquipoDto visitante;
	private int golesLocal;
	private int golesVisitante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EquipoDto getLocal() {
		return local;
	}

	public void setLocal(EquipoDto local) {
		this.local = local;
	}

	public EquipoDto getVisitante() {
		return visitante;
	}

	public void setVisitante(EquipoDto visitante) {
		this.visitante = visitante;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}
}
