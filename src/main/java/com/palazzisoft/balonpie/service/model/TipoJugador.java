package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "T_TIPO_JUGADOR")
public class TipoJugador implements Serializable {

	private static final long serialVersionUID = -6585459014112828131L;

	@Id
	@Column (name="F_ID")
	private Integer id;
	
	@Column (name="A_DESCRIPCION")
	private String  descripcion;
	
	public TipoJugador() {
		
	}
	
	public TipoJugador(Integer id) {
		this.id = id;
	}

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
	
	
}
