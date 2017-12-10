package com.palazzisoft.balonpie.service.dto;

public class EquiposEstadisticasDto implements Comparable<EquiposEstadisticasDto> {

	private static final int PUNTOS_GANADOR = 3;
	private static final int PUNTOS_EMPATE = 1;

	private EquipoDto equipo;
	private int puntos = 0;
	private int golesAFavor;
	private int golesRecibidos;

	public EquiposEstadisticasDto(EquipoDto equipoDto) {
		this.equipo = equipoDto;
	}

	public EquipoDto getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoDto equipo) {
		this.equipo = equipo;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getGolesAFavor() {
		return golesAFavor;
	}

	public void setGolesAFavor(int golesAFavor) {
		this.golesAFavor = golesAFavor;
	}

	public int getGolesRecibidos() {
		return golesRecibidos;
	}

	public void setGolesRecibidos(int golesRecibidos) {
		this.golesRecibidos = golesRecibidos;
	}

	public void agregarVictoria() {
		this.puntos = puntos + PUNTOS_GANADOR;
	}

	public void agregarEmpate() {
		this.puntos = puntos + PUNTOS_EMPATE;
	}

	public void agregarGolesAFavor(int goles) {
		this.golesAFavor = golesAFavor + goles;
	}

	public void agregarGolesRecibidos(int goles) {
		this.golesRecibidos = golesRecibidos + goles;
	}

	@Override
	public int compareTo(EquiposEstadisticasDto o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
