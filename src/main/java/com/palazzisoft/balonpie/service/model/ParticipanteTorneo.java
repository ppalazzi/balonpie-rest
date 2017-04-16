package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PARTICIPANTE_TORNEO")
public class ParticipanteTorneo implements Serializable {

	private static final long serialVersionUID = -6092390815160233352L;
	
	public ParticipanteTorneo() {
		this.participanteTorneoPk = new ParticipanteTorneoPK();
	}
	
	@Id
	private ParticipanteTorneoPK participanteTorneoPk;
	
	@Column(name = "A_ESCREADOR")
	private Boolean esCreador;
			
	@Column(name="torneo_F_ID",insertable = false, updatable = false )
	private Integer torneo;
	
	@Column(name="participante_F_ID", insertable = false, updatable = false )	
	private Integer participante;
	
	public Boolean getEsCreador() {
		return esCreador;
	}

	public void setEsCreador(Boolean esCreador) {
		this.esCreador = esCreador;
	}

	public Torneo getTorneo() {
		return participanteTorneoPk.getTorneo();
	}

	public void setTorneo(Torneo torneo) {
		this.participanteTorneoPk.setTorneo(torneo);
	}

	public Participante getParticipante() {
		return participanteTorneoPk.getParticipante();
	}

	public void setParticipante(Participante participante) {
		this.participanteTorneoPk.setParticipante(participante);
	}		
}
