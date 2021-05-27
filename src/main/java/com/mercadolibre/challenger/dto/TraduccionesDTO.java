package com.mercadolibre.challenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraduccionesDTO {
		
	private String nombrePaisEspanol;
	
	public TraduccionesDTO() {
		
	}

	@JsonProperty("es")
	public String getNombrePaisEspanol() {
		return nombrePaisEspanol;
	}

	@JsonProperty("es")
	public void setNombrePaisEspanol(String nombrePaisEspanol) {
		this.nombrePaisEspanol = nombrePaisEspanol;
	}
	
	
}
