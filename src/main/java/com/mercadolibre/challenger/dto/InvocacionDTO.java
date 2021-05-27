package com.mercadolibre.challenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvocacionDTO {
	
	public static final String KEY_CLASS = "INVOCACIONES";
	
	private String nombrePais;
	private double distanciaBuenosAires;
	private long invocaciones;
	
	public InvocacionDTO() {
		this.invocaciones = 1; 
	}

	@JsonProperty("nombrePais")
	public String getNombrePais() {
		return nombrePais;
	}
	@JsonProperty("nombrePais")
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	@JsonProperty("distanciaBuenosAires")
	public double getDistanciaBuenosAires() {
		return distanciaBuenosAires;
	}
	@JsonProperty("distanciaBuenosAires")
	public void setDistanciaBuenosAires(double distanciaBuenosAires) {
		this.distanciaBuenosAires = distanciaBuenosAires;
	}
	@JsonProperty("invocaciones")
	public long getInvocaciones() {
		return invocaciones;
	}
	@JsonProperty("invocaciones")
	public void setInvocaciones(long invocaciones) {
		this.invocaciones = invocaciones;
	}
	
	

}
