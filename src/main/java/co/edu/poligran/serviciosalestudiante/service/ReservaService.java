package co.edu.poligran.serviciosalestudiante.service;

import java.util.List;

import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

public interface ReservaService {
	List<ReservaDTO> consultarReservasVigentesPorUsuario(UsuarioDTO usuario);

	ReservaDTO crearReserva(UsuarioDTO usuario, BloqueDTO bloque);
}
