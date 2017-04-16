package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "T_TIPO_TORNEO")
public class TipoTorneo implements Serializable {

	private static final long serialVersionUID = -5165314052108161317L;

	@Id
	@Column (name = "F_ID")
	private Integer id;
	
	@Column (name = "A_DESCRIPCION")
	private String descripcion;
	
	@ManyToOne (fetch = FetchType.EAGER)
	private Presupuesto presupuesto;
	
	public TipoTorneo() {
		
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

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	
}
