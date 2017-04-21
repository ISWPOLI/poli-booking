package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

import java.util.List;

public interface ReservaService {
    ReservaDTO consultarReserva(Long idReserva);

    List<ReservaDTO> consultarReservasVigentesPorUsuario(UsuarioDTO usuario);

    ReservaDTO crearReserva(UsuarioDTO usuario, BloqueDTO bloque);
    
    List<ReservaDTO> consultarReservasVigentesGrafica();

    void eliminarReserva(Long idReserva);

    void eliminarReservasDelBloque(BloqueDTO bloque);
}
