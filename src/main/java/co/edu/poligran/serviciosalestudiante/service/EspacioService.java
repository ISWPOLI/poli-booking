package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;

import java.util.List;

public interface EspacioService {
    List<EspacioDTO> getCubiculosEstudio();

	EspacioDTO crearEspacio(EspacioDTO cubiculo);

	List<EspacioDTO> getEspaciosPorTipoEspacio(TipoEspacioDTO tipoEspacioDTO);
}
