package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="T_ADMINISTRADOR")
public class Administrador implements Serializable {
	
	private static final long serialVersionUID = -6400400690584861007L;

	@Id
	@Column (name="F_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	@Column (name="A_NOMBRE")
	private String  nombre;
	
	@Column (name="A_PASSWORD")
	private String  password;
	
	@Column (name="A_ESTADO")
	private int     estado;
	
	public Administrador() {
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
