package co.edu.poligran.serviciosalestudiante.service;

import java.util.Date;
import java.util.List;

import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;

public interface BloqueService {
    void generarBloques(EspacioDTO espacio, long numeroDeDias);

    List<BloqueDTO> consultarBloquesVigentesPorDiaYEspacio(Date dia, Long idEspacio);

    List<BloqueDTO> consultarBloquesVigentes();

    BloqueDTO consultarBloque(Long idBloque);
}
