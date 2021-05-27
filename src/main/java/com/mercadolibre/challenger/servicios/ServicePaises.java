package com.mercadolibre.challenger.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mercadolibre.challenger.dto.ResponseEstaUtilizacionDTO;
import com.mercadolibre.challenger.dto.ResponseLocalizacionDTO;
import com.mercadolibre.challenger.negocio.NegocioInvocacion;
import com.mercadolibre.challenger.negocio.NegocioPais;


@RestController()
@RequestMapping("/servicios/mercadolibre/paises")
public class ServicePaises {
	
	@Autowired
	private NegocioPais negocioPais;
	@Autowired
	private NegocioInvocacion negocioInvocacion;
	
	@GetMapping("/identificarPaisporIP")
	public ResponseEntity<Object> identificarPaisporIP(@RequestParam(name = "direccionIP") String direccionIP) {
		try {
			ResponseLocalizacionDTO localizacion = negocioPais.identificarPaisPorDireccionIP(direccionIP);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(localizacion);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());
		}
		
	}
	
	@GetMapping("/obtenerEstadisticasUsoServicio")
	public ResponseEntity<Object> obtenerEstadisticasUsoServicio() {
		try {
			ResponseEstaUtilizacionDTO estadisticas = negocioInvocacion.obtenerEstatisticasServicio();
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(estadisticas);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());
		}
		
	} 

}
