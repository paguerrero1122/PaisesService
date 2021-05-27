package com.mercadolibre.challenger.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mercadolibre.challenger.constants.Constantes;
import com.mercadolibre.challenger.dto.CotizacionEuroDTO;
import com.mercadolibre.challenger.exception.ChallengeException;
import com.mercadolibre.challenger.persistente.RedisRepository;

@Service
public class NegocioCotizacionEuro {
	
	private static final Logger logger = Logger.getLogger(NegocioCotizacionEuro.class);

	@Autowired
	private RedisRepository<CotizacionEuroDTO, String> redisRepository;
	@Autowired
	private Environment env;
	private RestTemplate restTemplate = new RestTemplate();
	
	
	public CotizacionEuroDTO consultarCotizacionMoneda(String moneda) {
		CotizacionEuroDTO cotizacion = new CotizacionEuroDTO();
		SimpleDateFormat formatoFecha = new SimpleDateFormat(Constantes.FORMATO_FECHA);
		String fechaActual = formatoFecha.format(new Date());
		try {
			cotizacion = consultarCotizacionMonedaBD(moneda);
			if(cotizacion != null) {
				if(!cotizacion.getDate().equals(fechaActual)) {
					cotizacion = obtenerCotizacionMonedaServicio(moneda);
					insertarCotizacionBD(cotizacion);
				}
			}else {
				cotizacion = obtenerCotizacionMonedaServicio(moneda);
				insertarCotizacionBD(cotizacion);
			}	
			
		} catch (Exception e) {
			String mensaje = "No se pudo consultar cotizacion de la moneda en Euros: "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
		return cotizacion;
	}
	
	public CotizacionEuroDTO obtenerCotizacionMonedaServicio(String moneda) {
		CotizacionEuroDTO cotizacion = new CotizacionEuroDTO();
		try {
			ResponseEntity<CotizacionEuroDTO> responseEntity = restTemplate.getForEntity(env.getProperty(Constantes.URL_SERVICE_COTIZACION_EURO)+moneda, CotizacionEuroDTO.class);
			cotizacion = responseEntity.getBody();
			cotizacion.setMoneda(moneda);
		} catch (Exception e) {
			String mensaje = "No se pudo consultar cotizacion de la moneda con el servicio rest internet : "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
		return cotizacion;
	}


	public void insertarCotizacionBD(CotizacionEuroDTO cotizacion) {
		try {
			redisRepository.saveHash(CotizacionEuroDTO.KEY_CLASS, cotizacion, cotizacion.getMoneda());
		} catch (Exception e) {
			String mensaje = "Error insertando hash de cotizacion en Redis : "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
	}
	
	public CotizacionEuroDTO consultarCotizacionMonedaBD(String atributo) {
		CotizacionEuroDTO cotizacion = null;
		try {
			cotizacion = redisRepository.findHashforKey(CotizacionEuroDTO.KEY_CLASS, atributo);
		} catch (Exception e) {
			String mensaje = "Error consultando hash cotizacion hash Redis : "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
		return cotizacion;
	}
	
	
	
}
