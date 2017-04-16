package com.palazzisoft.balonpie.service.model.enumeration;

public enum EEstadoParticipante {

	ACTIVO(1),INACTIVO(0);
	
	private Integer id;
	
	EEstadoParticipante(Integer id) {
		this.id = id;
	}
	
	public Integer getEstadoParticipante() {
		return id;
	}
}
