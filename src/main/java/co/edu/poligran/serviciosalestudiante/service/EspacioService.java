package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;

import java.util.List;

public interface EspacioService {
    List<EspacioDTO> getCubiculosEstudio();

	EspacioDTO crearEspacio(EspacioDTO cubiculo);
}
