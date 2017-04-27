package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public interface BloquePlantillaService {

    BloquePlantillaDTO crearBloquePlantilla(BloquePlantillaDTO bloquePlantillaDTO);

    List<BloquePlantillaDTO> consultarBloquesPlantillaPorTipoEspacio(String tipoEspacio);
    List<BloquePlantillaDTO> consultarBloquesPlantillaPorTipoEspacio(TipoEspacioDTO tipoEspacio);

    boolean existePlantillaParaTipoEspacioYDia(TipoEspacioDTO tipoEspacio, DayOfWeek dia, Date horaInicio);
}
