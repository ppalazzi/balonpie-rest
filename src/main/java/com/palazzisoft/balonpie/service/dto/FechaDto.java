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

	public int getGolesAchievedFromEquipo(EquipoDto equipoDto) {
		int goles = 0;

		for (PartidoDto partido : this.getPartidos()) {
			if (partido.getLocal().equals(equipoDto)) {
				goles = goles + partido.getGolesLocal();
			}
			if (partido.getVisitante().equals(equipoDto)) {
				goles = goles + partido.getGolesVisitante();
			}
		}

		return goles;
	}

	public int getGolesReceivedFromEquipo(EquipoDto equipoDto) {
		int goles = 0;

		for (PartidoDto partido : this.getPartidos()) {
			if (partido.getLocal().equals(equipoDto)) {
				goles = goles + partido.getGolesVisitante();
			}
			if (partido.getVisitante().equals(equipoDto)) {
				goles = goles + partido.getGolesLocal();
			}
		}

		return goles;
	}
}
