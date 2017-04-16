package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "T_PAIS")
public class Pais implements Serializable {

	private static final long serialVersionUID = -4652444313203350866L;

	@Id
	@Column (name = "F_ID")
	private Integer id;
	
	@Column (name = "A_ISOCODE")
	private String isoCode;
	
	@Column (name = "A_DESCRIPCION")
	private String descripcion;

	public Pais() {
		
	}
	
	public Pais(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
