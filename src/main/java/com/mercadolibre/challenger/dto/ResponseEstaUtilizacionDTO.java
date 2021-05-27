package com.mercadolibre.challenger.dto;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.challenger.constants.Constantes;
import com.mercadolibre.challenger.exception.ChallengeException;

public class ResponseEstaUtilizacionDTO {
	
	private static final Logger logger = Logger.getLogger(ResponseEstaUtilizacionDTO.class);

	private InvocacionDTO distanciaMasCercana;
	private InvocacionDTO distanciaMasLejanda;
	private String distanciaPromedio;
	
	public ResponseEstaUtilizacionDTO(){
		
	}

	@JsonProperty("distanciaMasCercana")
	public InvocacionDTO getDistanciaMasCercana() {
		return distanciaMasCercana;
	}
	@JsonProperty("distanciaMasCercana")
	public void setDistanciaMasCercana(InvocacionDTO distanciaMasCercana) {
		this.distanciaMasCercana = distanciaMasCercana;
	}
	@JsonProperty("distanciaMasLejanda")
	public InvocacionDTO getDistanciaMasLejanda() {
		return distanciaMasLejanda;
	}
	@JsonProperty("distanciaMasLejanda")
	public void setDistanciaMasLejanda(InvocacionDTO distanciaMasLejanda) {
		this.distanciaMasLejanda = distanciaMasLejanda;
	}
	@JsonProperty("distanciaPromedio")
	public String getDistanciaPromedio() {
		return distanciaPromedio;
	}
	@JsonProperty("distanciaPromedio")
	public void setDistanciaPromedio(String distanciaPromedio) {
		this.distanciaPromedio = distanciaPromedio;
	}
	
	public static ResponseEstaUtilizacionDTO getResponseEstadistica(Set<InvocacionDTO> invocaciones,Set<InvocacionDTO> masCercana , Set<InvocacionDTO> masLejana) {
		ResponseEstaUtilizacionDTO estatisticaUtilizacion = new ResponseEstaUtilizacionDTO();
		try {
			estatisticaUtilizacion.setDistanciaMasCercana(obtenerInvocacion(masCercana));
			estatisticaUtilizacion.setDistanciaMasLejanda(obtenerInvocacion(masLejana));
			estatisticaUtilizacion.setDistanciaPromedio(obtenerDistanciaPromedio(invocaciones));
		} catch (Exception e) {
			String mensaje = "No se pudo construir repuesta servicio estadisticas de uso: "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(e.getMessage());
		}
		return estatisticaUtilizacion;
	}
	
	public static InvocacionDTO obtenerInvocacion(Set<InvocacionDTO> setInvocacion) {
		InvocacionDTO invocacion = new InvocacionDTO();
		for (Iterator <InvocacionDTO> it= setInvocacion.iterator(); it.hasNext();) {
			invocacion = it.next();
		}
		return invocacion;
	}
	
	public static String obtenerDistanciaPromedio(Set<InvocacionDTO> invocaciones) {
		long totalInvocaciones=0;
		double promedio=0;
		for (Iterator <InvocacionDTO> it= invocaciones.iterator(); it.hasNext();) {
			InvocacionDTO invocacion = it.next();
			totalInvocaciones += invocacion.getInvocaciones();
			promedio += invocacion.getDistanciaBuenosAires()*invocacion.getInvocaciones();
		}
		promedio = promedio / totalInvocaciones;
		return promedio + Constantes.KMS;
	}
	
	
}
