package com.palazzisoft.balonpie.service.dto;

import java.util.List;

public class TorneoDto {

	private Integer id;
	private String descripcion;
	private String fechaCreacion;
	private String estado;
	private String fechaInicio;
	private String fechaFin;
	private List<EquipoDto> equipos;
	private ParticipanteDto participante;
	private FixtureDto fixture;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<EquipoDto> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoDto> equipos) {
		this.equipos = equipos;
	}

	public ParticipanteDto getParticipante() {
		return participante;
	}

	public void setParticipante(ParticipanteDto participante) {
		this.participante = participante;
	}

	public FixtureDto getFixture() {
		return fixture;
	}

	public void setFixture(FixtureDto fixture) {
		this.fixture = fixture;
	}
	
	

}
