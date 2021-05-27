package com.mercadolibre.challenger.negocio;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mercadolibre.challenger.constants.Constantes;
import com.mercadolibre.challenger.dto.CotizacionEuroDTO;
import com.mercadolibre.challenger.dto.LocalizacionIPDTO;
import com.mercadolibre.challenger.dto.PaisDTO;
import com.mercadolibre.challenger.dto.ResponseLocalizacionDTO;
import com.mercadolibre.challenger.exception.ChallengeException;
import com.mercadolibre.challenger.persistente.RedisRepository;

@Service
public class NegocioPais {
	
	private static final Logger logger = Logger.getLogger(NegocioPais.class);

	@Autowired
	private RedisRepository<PaisDTO, String> redisRepository;
	
	@Autowired
	private NegocioCotizacionEuro negocioCotizacionEuro;
	
	@Autowired
	private NegocioInvocacion negocioInvocacion;
	
	@Autowired
	private Environment env;
	private RestTemplate restTemplate = new RestTemplate();
	
		
	public ResponseLocalizacionDTO identificarPaisPorDireccionIP(String direccionIP) {
		try {
			ResponseEntity<LocalizacionIPDTO> responseEntity = restTemplate.getForEntity(env.getProperty(Constantes.URL_SERVICE_LOCALIZACION_PAIS)+direccionIP, LocalizacionIPDTO.class);
			LocalizacionIPDTO localizacion = responseEntity.getBody();
			PaisDTO pais= new PaisDTO();
			CotizacionEuroDTO cotizacionMoneda = new CotizacionEuroDTO();
			if(localizacion !=null) {
				pais = consultarInformacionPaisBD(localizacion.getCountryCode());
				cotizacionMoneda = negocioCotizacionEuro.consultarCotizacionMoneda(pais.getCurrencies()[0].getCode());
				negocioInvocacion.insertarInvocacionServicio(pais.getName() , pais.getKmaBuenosAires());
			}
			logger.info("Localizacion de la IP "+direccionIP+" realizada de forma correcta");
			return ResponseLocalizacionDTO.getResponseLocalizacion(pais, cotizacionMoneda , direccionIP);
		} catch (Exception e) {
			String mensaje = "No se pudo identificar localizacion por IP: "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
	}
	
	
	public PaisDTO consultarInformacionPaisBD(String nombrePais) {
		PaisDTO pais = null;
		try {
			pais = redisRepository.findHashforKey(PaisDTO.KEY_CLASS, nombrePais);
		} catch (Exception e) {
			String mensaje = "No se pudo insertar hash pais en redis server: "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
		return pais;
	}
	
}
