package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ParticipanteTorneoPK implements Serializable {

	private static final long serialVersionUID = -7185771403882268835L;

	public ParticipanteTorneoPK() {

	}
		
	@ManyToOne
	private Participante participante;
	
	@ManyToOne
	private Torneo torneo;
	
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((participante == null) ? 0 : participante.hashCode());
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
		ParticipanteTorneoPK other = (ParticipanteTorneoPK) obj;
		if (participante == null) {
			if (other.participante != null)
				return false;
		} else if (!participante.equals(other.participante))
			return false;
		if (torneo == null) {
			if (other.torneo != null)
				return false;
		} else if (!torneo.equals(other.torneo))
			return false;
		return true;
	}	
	
}
