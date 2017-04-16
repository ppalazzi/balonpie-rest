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

@Entity
@Table(name = "T_FIXTURE")
public class Fixture implements Serializable {

	private static final long serialVersionUID = -2546468371021022976L;

	public Fixture() {
		this.fechas = newArrayList();
	}
	
	@Id
	@Column(name = "F_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
		
	@ManyToOne(fetch = FetchType.EAGER)	
	private Torneo torneo;
	
	@OneToMany(mappedBy = "fixture", cascade = {CascadeType.ALL})	
	private List<Fecha> fechas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	public List<Fecha> getFechas() {
		return fechas;
	}

	public void setFechas(List<Fecha> fechas) {
		this.fechas = fechas;
	}
		
	public void agregarFecha(Fecha fecha) {
		this.fechas.add(fecha);
	}
}
