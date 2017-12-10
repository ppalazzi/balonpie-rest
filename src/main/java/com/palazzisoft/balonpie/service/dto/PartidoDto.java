package com.palazzisoft.balonpie.service.dto;

public class PartidoDto {

	private Integer id;
	private EquipoDto local;
	private EquipoDto visitante;
	private int golesLocal;
	private int golesVisitante;
	private boolean estado;
	private boolean jugado;

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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean isJugado() {
		return jugado;
	}

	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}

	public int getGolesFromEquipo(EquipoDto equipoDto) {
		if (!equipoDto.equals(local) || !equipoDto.equals(visitante)) {
			throw new IllegalArgumentException("El equipo no Participó del Partido");
		}

		if (equipoDto.equals(local)) {
			return golesLocal;
		} else {
			return golesVisitante;
		}
	}
}
