package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * 
 * @author ppalazzi
 *
 */
@Embeddable
public class EquipoTorneoPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Equipo equipo;
	
	@ManyToOne
	private Torneo torneo;
	
	public EquipoTorneoPk() {
		
	}
		
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipo == null) ? 0 : equipo.hashCode());
		result = prime * result + ((torneo == null) ? 0 : torneo.hashCode());
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
		EquipoTorneoPk other = (EquipoTorneoPk) obj;
		if (equipo == null) {
			if (other.equipo != null)
				return false;
		} else if (!equipo.equals(other.equipo))
			return false;
		if (torneo == null) {
			if (other.torneo != null)
				return false;
		} else if (!torneo.equals(other.torneo))
			return false;
		return true;
	}
	
}
