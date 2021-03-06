package com.palazzisoft.balonpie.service.dto;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class EquipoDto implements Comparable<EquipoDto> {

	private Integer id;
	private String nombre;
	private String descripcion;
	private Date fechaCreacion;
	private Integer estado;
	private ParticipanteDto participante;
	private Long presupuesto;
	private Set<JugadorDto> jugadores;
	private Long puntos;

	public EquipoDto() {

	}

	public EquipoDto(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public ParticipanteDto getParticipante() {
		return participante;
	}

	public void setParticipante(ParticipanteDto participante) {
		this.participante = participante;
	}

	public Long getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Long presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Set<JugadorDto> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<JugadorDto> jugadores) {
		this.jugadores = jugadores;
	}

	public Long getPuntos() {
		return puntos;
	}

	public void setPuntos(Long puntos) {
		this.puntos = puntos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EquipoDto equipoDto = (EquipoDto) o;
		return Objects.equals(id, equipoDto.id);
	}

	@Override
	public int hashCode() {
		return Objects
				.hash(id, nombre, descripcion, fechaCreacion, estado, participante, presupuesto, jugadores, puntos);
	}

    @Override
    public int compareTo(EquipoDto o) {
        return o.getId().compareTo(this.getId());
    }
}
