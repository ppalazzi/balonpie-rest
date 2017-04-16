package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "T_PRESUPUESTO")
public class Presupuesto implements Serializable {

	private static final long serialVersionUID = 4324431583642034333L;

	@Id
	@Column (name = "F_ID")
	private Integer id;
	
	@Column (name = "A_DESCRIPCION")
	private String descripcion;
	
	@Column (name = "A_VALOR")
	private Long valor;
	
	public Presupuesto() {
		
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

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	
}
