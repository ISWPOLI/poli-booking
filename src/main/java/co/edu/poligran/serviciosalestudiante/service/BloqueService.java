package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;

import java.util.Date;
import java.util.List;

public interface BloqueService {
    void generarBloques(EspacioDTO espacio, long numeroDeDias);

    List<BloqueDTO> consultarBloquesVigentesPorDiaYTipoEspacio(Date dia, TipoEspacioDTO tipoEspacio);

    List<BloqueDTO> consultarBloquesVigentesPorTipoEspacio(TipoEspacioDTO tipoEspacio);

    BloqueDTO consultarBloque(Long idBloque);
}
