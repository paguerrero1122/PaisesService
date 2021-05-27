package com.mercadolibre.challenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonedaDTO {

	private String code; // Codigo Moneda
	private String name; // Nombre Moneda
	private String symbol; // symbolo Moneda
	
	public MonedaDTO() {
		
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("symbol")
	public String getSymbol() {
		return symbol;
	}

	@JsonProperty("symbol")
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
}
