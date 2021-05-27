package com.mercadolibre.challenger.dto;

import java.util.Date;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.challenger.constants.Constantes;
import com.mercadolibre.challenger.exception.ChallengeException;
import com.mercadolibre.challenger.utils.Utils;

public class ResponseLocalizacionDTO {
	
	private static final Logger logger = Logger.getLogger(ResponseLocalizacionDTO.class);

	private String direccionIP;
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-5")
	private Date   fechaActual;
	private String nombrePais;
	private String codigoIso;
	private String idiomas;
	private String moneda;
	private String zonasHorarias;
	private String distanciaEstimado;
	
	
	public ResponseLocalizacionDTO() {
		this.fechaActual = new Date();
	}

	@JsonProperty("direccionIP")
	public String getDireccionIP() {
		return direccionIP;
	}

	@JsonProperty("direccionIP")
	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}

	@JsonProperty("fechaActual")
	public Date getFechaActual() {
		return fechaActual;
	}

	@JsonProperty("fechaActual")
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	@JsonProperty("nombrePais")
	public String getNombrePais() {
		return nombrePais;
	}

	@JsonProperty("nombrePais")
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	@JsonProperty("codigoIso")
	public String getCodigoIso() {
		return codigoIso;
	}

	@JsonProperty("codigoIso")
	public void setCodigoIso(String codigoIso) {
		this.codigoIso = codigoIso;
	}

	@JsonProperty("idiomas")
	public String getIdiomas() {
		return idiomas;
	}

	@JsonProperty("idiomas")
	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	@JsonProperty("zonasHorarias")
	public String getZonasHorarias() {
		return zonasHorarias;
	}

	@JsonProperty("zonasHorarias")
	public void setZonasHorarias(String zonasHorarias) {
		this.zonasHorarias = zonasHorarias;
	}

	@JsonProperty("distanciaEstimado")
	public String getDistanciaEstimado() {
		return distanciaEstimado;
	}

	@JsonProperty("distanciaEstimado")
	public void setDistanciaEstimado(String distanciaEstimado) {
		this.distanciaEstimado = distanciaEstimado;
	}

	@JsonProperty("moneda")
	public String getMoneda() {
		return moneda;
	}
	@JsonProperty("moneda")
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public static ResponseLocalizacionDTO getResponseLocalizacion(PaisDTO pais , CotizacionEuroDTO cotizacionMoneda , String ip) {
		ResponseLocalizacionDTO responsePaisLocalizacion = new ResponseLocalizacionDTO();
		try {			
			responsePaisLocalizacion.setMoneda(obtenerMonedaCotizacion(pais.getCurrencies(), cotizacionMoneda));
			responsePaisLocalizacion.setIdiomas(obtenerIdiomas(pais.getLanguajes()));
			responsePaisLocalizacion.setDireccionIP(ip);
			responsePaisLocalizacion.setNombrePais(pais.getTranslations().getNombrePaisEspanol()+"("+pais.getName()+")");
			responsePaisLocalizacion.setCodigoIso(pais.getAlpha2Code());
			responsePaisLocalizacion.setZonasHorarias(obtenerHorasPais(pais.getTimezones()));
			responsePaisLocalizacion.setDistanciaEstimado(pais.getKmaBuenosAires() + Constantes.KMS);
		} catch (Exception e) {
			String mensaje = "No se pudo construir repuesta servicio localizacion: "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(e.getMessage());
			
		}
		return responsePaisLocalizacion;
	}
	
	private static String obtenerIdiomas(IdiomaDTO[] idiomas) {
		String idiomasPais="";
		if(idiomas != null && idiomas.length >0) {
			for (int i = 0; i < idiomas.length; i++) {
				idiomasPais += idiomas[0].getName() + "("+idiomas[0].getIso639_1()+") ";
			}
		}
		return idiomasPais;
	}
	
	private static String obtenerHorasPais(String[] timezones) {
		String hora ="";
		if(timezones != null && timezones.length >0) {
			for (int i = 0; i < timezones.length; i++) {
				hora += Utils.calcularHoraUTC(timezones[i]);
			}
		}
		return hora;
	}
	
	private static String obtenerMonedaCotizacion(MonedaDTO[] monedas, CotizacionEuroDTO cotizacion) {
		String monedasPais = "";
		MonedaDTO moneda = new MonedaDTO();
		if(monedas != null && monedas.length >0) {
			moneda= monedas[0];
			monedasPais = moneda.getName();
		}
		if(cotizacion != null ) {
			monedasPais += "( 1 EUR = "+cotizacion.getRates().get(moneda.getCode())+" "+moneda.getSymbol()+" )";
		}
		return monedasPais;
	}
	
	
}
