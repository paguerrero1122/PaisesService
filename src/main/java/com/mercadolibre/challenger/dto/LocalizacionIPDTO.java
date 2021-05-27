package com.mercadolibre.challenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalizacionIPDTO {

	private String countryCode;
	private String countryCode3;
	private String countryName;
	
	public LocalizacionIPDTO() {
		
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}
	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@JsonProperty("countryCode3")
	public String getCountryCode3() {
		return countryCode3;
	}
	@JsonProperty("countryCode3")
	public void setCountryCode3(String countryCode3) {
		this.countryCode3 = countryCode3;
	}
	@JsonProperty("countryName")
	public String getCountryName() {
		return countryName;
	}
	@JsonProperty("countryName")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
}
