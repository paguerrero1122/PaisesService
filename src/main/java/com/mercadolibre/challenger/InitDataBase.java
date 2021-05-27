package com.mercadolibre.challenger;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.mercadolibre.challenger.constants.Constantes;
import com.mercadolibre.challenger.dto.PaisDTO;
import com.mercadolibre.challenger.exception.ChallengeException;
import com.mercadolibre.challenger.persistente.RedisRepository;
import com.mercadolibre.challenger.utils.Utils;

public class InitDataBase {
	
	private static final Logger logger = Logger.getLogger(InitDataBase.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private RedisRepository<PaisDTO, String> redisRepository;
	
	@PostConstruct
	public void init() {
		try {
			logger.info("Inicializando base de datos de paises");
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<PaisDTO[]> listadoPaises = restTemplate.getForEntity(env.getProperty(Constantes.URL_SERVICE_PAISES) , PaisDTO[].class);
			ResponseEntity<PaisDTO[]> pais = restTemplate.getForEntity(env.getProperty(Constantes.URL_SERVICE_PAIS_NOMBRE)+Constantes.KEY_ARGENTINA , PaisDTO[].class);
			PaisDTO[] paises = listadoPaises.getBody();
			PaisDTO argentina = pais.getBody()[0];
			logger.info("Calculando distancias de cada pais a Buenos Aires, Argentina");
			for (int i = 0; i < paises.length; i++) {
				if(paises[i].getLatlng() != null && paises[i].getLatlng().length ==2) {
					paises[i].setKmaBuenosAires(Utils.obtenerDistanciaKmaBuenosAires(argentina.getLatlng()[0], argentina.getLatlng()[1], paises[i].getLatlng()[0], paises[i].getLatlng()[1]));
				}
			}
			redisRepository.saveListHash(PaisDTO.KEY_CLASS, PaisDTO.transformarArraytoMap(paises));
			logger.info("Base de datos de paises inicializada de forma correcta");
		} catch (Exception e) {
			String mensaje = "No se pudo inicializar base de datos paises: "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
		
	}

}
