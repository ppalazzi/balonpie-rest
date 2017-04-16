package com.palazzisoft.balonpie.service.model.enumeration;

public enum EEstado {

	ACTIVO(1),INACTIVO(0);
	
	private Integer id;

	EEstado(Integer id) {
		this.id = id;
	}
	
	public Integer getEstado() {
		return id;
	}
	
}
