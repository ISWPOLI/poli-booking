package co.edu.poligran.serviciosalestudiante.service;

import java.util.List;

import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;

public interface EspacioService {
	List<EspacioDTO> getCubiculos();

	EspacioDTO crearEspacio(EspacioDTO cubiculo);
}
