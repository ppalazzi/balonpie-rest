package com.palazzisoft.balonpie.service.model;

import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="T_PARTICIPANTE")
public class Participante implements Serializable {

	private static final long serialVersionUID = -6280886379763145897L;
	
	@Id
	@Column (name="F_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)		
	private Integer id;
	
	@Column (name="A_NOMBRE")
	private String  nombre;
	
	@Column (name="A_APELLIDO")
	private String apellido;
	
	@Column (name="A_EMAIL")	
	private String email;
	
	@Column (name="A_FECHANACIMIENTO")	
	private Date fechaNacimiento;
			
	@Column (name="A_ESTADO")	
	private Integer estado; 
	
	@Column (name="A_PASSWORD")
	private String password;
	
	@Transient
	private List<Torneo> torneos;
		
	public Participante() {
		this.torneos = newArrayList();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
		
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Torneo> getTorneos() {
		return torneos;
	}
	
	public void setTorneos(List<Torneo> torneos) {
		this.torneos = torneos;
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
		Participante other = (Participante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
