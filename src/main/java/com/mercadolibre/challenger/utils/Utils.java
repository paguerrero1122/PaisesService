package com.mercadolibre.challenger.utils;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.mercadolibre.challenger.constants.Constantes;
import com.mercadolibre.challenger.exception.ChallengeException;

public class Utils {

	private static final Logger logger = Logger.getLogger(Utils.class);
	
	public static double obtenerDistanciaKmaBuenosAires(double latDesde, double lonDesde , double latHasta, double lonHasta) {
		double distanciaKm =0;
		try {
			double radlatDesde = Math.toRadians(latDesde);
			double radlonDesde = Math.toRadians(lonDesde);
			double radlatHasta = Math.toRadians(latHasta);
			double radlonHasta = Math.toRadians(lonHasta);
			distanciaKm = Math.round(Constantes.DIAMETRO_TIERRA*Math.asin(Math.sqrt(Math.pow(Math.sin((radlatHasta-radlatDesde)/2), 2)+Math.cos(radlatDesde)*Math.cos(radlatHasta)*Math.pow(Math.sin((radlonHasta-radlonDesde)/2), 2))));
		} catch (Exception e) {
			String mensaje = "No se pudo calcular distancia del pais a Buenos Aires : "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
		return distanciaKm;
	}
	
	
	public static String calcularHoraUTC(String timeZone) {
		String hora = "";
		String UTC = timeZone;
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(Constantes.FORMATO_HORA);
		try {
			if(!Constantes.UTC.equals(timeZone)) {
				UTC = timeZone.replaceAll(Constantes.UTC, "");
				ZoneOffset zona = ZoneOffset.of(UTC);
				OffsetDateTime fecha = OffsetDateTime.ofInstant(Instant.now(), zona);
				
				hora = fmt.format(fecha)+" ("+timeZone+") "; 
			}else {
				OffsetDateTime fecha = OffsetDateTime.now( ZoneOffset.UTC );
				hora = fmt.format(fecha)+" ("+timeZone+") "; 
			}
		return hora;	
		} catch (Exception e) {
			String mensaje = "No se pudo calcular hora del timezone  "+timeZone+" :"+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
	}
}
