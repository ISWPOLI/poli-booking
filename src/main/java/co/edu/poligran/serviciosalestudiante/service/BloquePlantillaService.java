package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;

import java.util.List;

public interface BloquePlantillaService {

    BloquePlantillaDTO crearBloquePlantilla(BloquePlantillaDTO bloquePlantillaDTO);

    List<BloquePlantillaDTO> consultarBloquesPlantillaPorTipoEspacio(TipoEspacioDTO tipoEspacio);
}
