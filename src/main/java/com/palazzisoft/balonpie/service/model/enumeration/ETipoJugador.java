package com.palazzisoft.balonpie.service.model.enumeration;

public enum ETipoJugador {

	ARQUERO(0),DEFENSOR(1),MEDIOCAMPISTA(2),ATACANTE(3);	
	
	private Integer id;
		
	ETipoJugador(Integer id) {
		this.id = id;
	}
	
	public Integer tipoJugador() {
		return id;
	}
	
	public Integer getByDescription(String description) {
		if (ARQUERO.name().equals(description)) {
			return ARQUERO.id;
		}		
		if (DEFENSOR.name().equals(description)) {
			return DEFENSOR.id;
		}
		if (ATACANTE.name().equals(description)) {
			return ATACANTE.id;
		}
				
		return ATACANTE.id;
	}
}
