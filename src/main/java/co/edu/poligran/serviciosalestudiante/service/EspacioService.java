package co.edu.poligran.serviciosalestudiante.service;

import java.util.List;

import co.edu.poligran.serviciosalestudiante.service.dto.CubiculoDTO;

public interface EspacioService {
	List<CubiculoDTO> getCubiculos();
	CubiculoDTO crearCubiculo(CubiculoDTO cubiculo);
}
