package com.palazzisoft.balonpie.service.dto;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.System.currentTimeMillis;

import java.util.List;
import java.util.Random;

public class JugadorDto {

	private Integer id;
	private String nombre;
	private String apellido;
	private Integer velocidad;
	private Integer remate;
	private Integer habilidad;
	private Integer fisico;
	private Integer estado;
	private Integer valor;
	private TipoJugadorDto tipoJugador;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Integer velocidad) {
		this.velocidad = velocidad;
	}

	public Integer getRemate() {
		return remate;
	}

	public void setRemate(Integer remate) {
		this.remate = remate;
	}

	public Integer getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(Integer habilidad) {
		this.habilidad = habilidad;
	}

	public Integer getFisico() {
		return fisico;
	}

	public void setFisico(Integer fisico) {
		this.fisico = fisico;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public TipoJugadorDto getTipoJugador() {
		return tipoJugador;
	}

	public void setTipoJugador(TipoJugadorDto tipoJugadorDto) {
		this.tipoJugador = tipoJugadorDto;
	}

	public int calculateValor() {
		List<Integer> values = newArrayList(getFisico(), getHabilidad(), getRemate(), getVelocidad());
		Double average = values.stream().mapToInt(p -> p.intValue()*100).average().getAsDouble();
		
		Random random = new Random(currentTimeMillis());
		Double randomValue = 10000 + (15000 - 10000) * random.nextDouble();
		
		int ret = Double.valueOf(randomValue * average).intValue();

		return ret;
		
	}
}
