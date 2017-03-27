package co.edu.poligran.serviciosalestudiante.service;

import java.util.Date;
import java.util.List;

import co.edu.poligran.serviciosalestudiante.entities.TipoEspacio;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;

public interface BloqueService {
    void generarBloques(EspacioDTO espacio, long numeroDeDias);

    List<BloqueDTO> consultarBloquesVigentesPorDiaYTipoEspacio(Date dia, TipoEspacio tipoEspacio);

    List<BloqueDTO> consultarBloquesVigentesPorTipoEspacio(TipoEspacio tipoEspacio);

    BloqueDTO consultarBloque(Long idBloque);
}
