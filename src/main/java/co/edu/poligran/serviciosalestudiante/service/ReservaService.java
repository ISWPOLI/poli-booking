package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

import java.util.List;

public interface ReservaService {
    ReservaDTO consultarReserva(Long idReserva);

    List<ReservaDTO> consultarReservasVigentesPorUsuario(String usuario) throws UserNotFoundException;

    List<ReservaDTO> consultarReservasVigentesPorUsuario(UsuarioDTO usuario) throws UserNotFoundException;

    ReservaDTO crearReserva(UsuarioDTO usuario, BloqueDTO bloque);
    
    List<ReservaDTO> consultarReservasVigentesGrafica();
    
    List<ReservaDTO> consultarHistorico();

    void eliminarReserva(Long idReserva);

    void eliminarReservasDelBloque(BloqueDTO bloque);
    
    
}
