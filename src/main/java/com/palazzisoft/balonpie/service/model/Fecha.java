package com.palazzisoft.balonpie.service.model;

import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

@Entity
@Table(name = "T_FECHA")
public class Fecha implements Serializable {
	
	private static final long serialVersionUID = 4372305609859171450L;
	
	public Fecha() {
		this.partidos = newArrayList();
	}
	
	@Id
	@Column(name = "F_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@ManyToOne (fetch=FetchType.EAGER)
	private Fixture fixture;
	
	@Column(name = "A_NUMERO")
	private Integer numero;
	
	@OneToMany(mappedBy = "fecha", cascade = {CascadeType.ALL})	
	private List<Partido> partidos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
		
	public void agregarPartido(Partido partido) {
		this.partidos.add(partido);
	}
}
