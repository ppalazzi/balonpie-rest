package com.palazzisoft.balonpie.service.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author ppalazzi
 *
 */
@Entity
@Table(name = "T_EQUIPO_TORNEO")
public class EquipoTorneo implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EquipoTorneoPk id;

	public EquipoTorneoPk getId() {
		return id;
	}

	public void setId(EquipoTorneoPk id) {
		this.id = id;
	}

}
