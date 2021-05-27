package com.mercadolibre.challenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdiomaDTO {
	
	private String name; // Nombre idioma Ingles
	private String iso639_1; // Codigo Iso Pais;
	
	public IdiomaDTO() {
		
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("iso639_1")
	public String getIso639_1() {
		return iso639_1;
	}
	
	@JsonProperty("iso639_1")
	public void setIso639_1(String iso639_1) {
		this.iso639_1 = iso639_1;
	}
	
}
