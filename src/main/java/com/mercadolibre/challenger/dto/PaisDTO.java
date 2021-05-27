package com.mercadolibre.challenger.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaisDTO {
	
	public static final String KEY_CLASS = "PAISES";

	private String name; //Nombre Pais en Ingles;
	private String alpha2Code; //Codigo ISO Pais
	private IdiomaDTO[] languages; //Idiomas del Pais
	private String[] timezones; //Zonas horarias
	private double[] latlng; //Coordenadas
	private double kmaBuenosAires; // Distancia en km a Buenos Aires Argentina
	private MonedaDTO[] currencies; // Monedas Pais 
	private TraduccionesDTO translations; // Nombre Pais en varios idiomas
	
	public PaisDTO() {
		
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("alpha2Code")
	public String getAlpha2Code() {
		return alpha2Code;
	}
	@JsonProperty("alpha2Code")
	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}
	@JsonProperty("languages")
	public IdiomaDTO[] getLanguajes() {
		return languages;
	}
	@JsonProperty("languages")
	public void setLanguajes(IdiomaDTO[] languajes) {
		this.languages = languajes;
	}
	@JsonProperty("timezones")
	public String[] getTimezones() {
		return timezones;
	}
	@JsonProperty("timezones")
	public void setTimezones(String[] timezones) {
		this.timezones = timezones;
	}
	@JsonProperty("latlng")
	public double[] getLatlng() {
		return latlng;
	}
	@JsonProperty("latlng")
	public void setLatlng(double[] latlng) {
		this.latlng = latlng;
	}
	@JsonProperty("kmaBuenosAires")
	public double getKmaBuenosAires() {
		return kmaBuenosAires;
	}
	@JsonProperty("kmaBuenosAires")
	public void setKmaBuenosAires(double kmaBuenosAires) {
		this.kmaBuenosAires = kmaBuenosAires;
	}
	@JsonProperty("currencies")
	public MonedaDTO[] getCurrencies() {
		return currencies;
	}
	@JsonProperty("currencies")
	public void setCurrencies(MonedaDTO[] currencies) {
		this.currencies = currencies;
	}
	
	@JsonProperty("translations")	
	public TraduccionesDTO getTranslations() {
		return translations;
	}

	@JsonProperty("translations")
	public void setTranslations(TraduccionesDTO translations) {
		this.translations = translations;
	}

	public static Map<String, PaisDTO> transformarArraytoMap(PaisDTO[] data){
		Map<String, PaisDTO> paisesMap = new HashMap<String, PaisDTO>();
		try {
			if(data != null) {
				for (int i = 0; i < data.length; i++) {
					PaisDTO pais = data[i];
					paisesMap.put(pais.getAlpha2Code(), pais);
				}
			}
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		return paisesMap;
	}
	
}
