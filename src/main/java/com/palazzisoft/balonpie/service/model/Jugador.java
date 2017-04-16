package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table (name="T_JUGADOR")
public class Jugador implements Serializable {

	private static final long serialVersionUID = 9189577911540530290L;
	
	@Id
	@Column (name="T_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
		
	@Column (name="A_NOMBRE")
	private String  nombre;
	
	@Column (name="A_APELLIDO")
	private String apellido;
	
	@Column (name="A_FECHANACIMIENTO")	
	private Date fechaNacimiento;
	
	@Column (name="A_VELOCIDAD")	
	private Integer velocidad;
	
	@Column (name="A_REMATE")
	private Integer remate;
	
	@Column (name="A_HABILIDAD")
	private Integer habilidad;
	
	@Column (name="A_FISICO")
	private Integer fisico;
	
	@Column (name="A_ESTADO")
	private Integer estado;
	
	@Column (name="A_VALOR")
	private Integer valor;
	
	@ManyToOne (fetch=FetchType.EAGER)
	private TipoJugador tipoJugador;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "jugador")
	private List<EquipoJugador> equipoJugadores;
	
	public Jugador() {
		
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
		
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public TipoJugador getTipoJugador() {
		return tipoJugador;
	}

	public void setTipoJugador(TipoJugador tipoJugador) {
		this.tipoJugador = tipoJugador;
	}	
	
	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}	
	
	public List<EquipoJugador> getEquipoJugadores() {
		return equipoJugadores;
	}

	public void setEquipoJugadores(List<EquipoJugador> equipoJugadores) {
		this.equipoJugadores = equipoJugadores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
