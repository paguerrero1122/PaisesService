package com.mercadolibre.challenger.negocio;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mercadolibre.challenger.dto.InvocacionDTO;
import com.mercadolibre.challenger.dto.ResponseEstaUtilizacionDTO;
import com.mercadolibre.challenger.exception.ChallengeException;
import com.mercadolibre.challenger.persistente.RedisRepository;

@Service
public class NegocioInvocacion {

	private static final Logger logger = Logger.getLogger(NegocioInvocacion.class);
	
	@Autowired
	private RedisRepository<InvocacionDTO, String> redisRepository;
	
	
	public void insertarInvocacionServicio(String pais , double distanciaBuenosAires) {
		try {
			InvocacionDTO invocacion = null;
			Set<InvocacionDTO> invocaciones = redisRepository.getObjectbyScrore(InvocacionDTO.KEY_CLASS, distanciaBuenosAires);
			for (Iterator <InvocacionDTO> it= invocaciones.iterator(); it.hasNext();) {
				invocacion = it.next();
			}
			if(invocacion == null) {
				invocacion = new InvocacionDTO();
				invocacion.setNombrePais(pais);
				invocacion.setDistanciaBuenosAires(distanciaBuenosAires);
			}else {
				redisRepository.removeElementSet(InvocacionDTO.KEY_CLASS, invocacion);
				invocacion.setInvocaciones(invocacion.getInvocaciones()+1);
				
			}
			redisRepository.saveOnSet(InvocacionDTO.KEY_CLASS, invocacion, distanciaBuenosAires);
		} catch (Exception e) {
			String mensaje = "Error insertando set invocacion servicio localización : "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
	}
	
	
	public ResponseEstaUtilizacionDTO obtenerEstatisticasServicio() {
		try {
			Set<InvocacionDTO> invocaciones = redisRepository.getSetRange(InvocacionDTO.KEY_CLASS, 0, -1);
			Set<InvocacionDTO> invocacionMasCercana = redisRepository.getSetRange(InvocacionDTO.KEY_CLASS, 0, 0);
			Set<InvocacionDTO> invocacionMasLejana = redisRepository.getSetRange(InvocacionDTO.KEY_CLASS, -1, -1);
			return ResponseEstaUtilizacionDTO.getResponseEstadistica(invocaciones, invocacionMasCercana, invocacionMasLejana);
		} catch (Exception e) {
			String mensaje = "Error obteniendo estadisticas de utilizacion del servicio : "+e.getMessage();
			logger.error(mensaje);
			throw new ChallengeException(mensaje);
		}
	}
}
