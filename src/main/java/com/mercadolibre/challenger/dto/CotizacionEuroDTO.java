package com.mercadolibre.challenger.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CotizacionEuroDTO {
	
	public static final String KEY_CLASS= "COTIZACION";
	
	private String moneda;
	private String base;
	private String date;
	private Map<String, Double> rates;
	
	
	public CotizacionEuroDTO() {
		
	}

	@JsonProperty("base")
	public String getBase() {
		return base;
	}

	@JsonProperty("base")
	public void setBase(String base) {
		this.base = base;
	}
	
	@JsonProperty("date")
	public String getDate() {
		return date;
	}
	@JsonProperty("date")
	public void setDate(String date) {
		this.date = date;
	}

	@JsonProperty("rates")
	public Map<String, Double> getRates() {
		return rates;
	}

	@JsonProperty("rates")
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	@JsonProperty("moneda")
	public String getMoneda() {
		return moneda;
	}

	@JsonProperty("moneda")
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	


}
