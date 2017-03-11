package co.edu.poligran.serviciosalestudiante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.service.ReservaService;
import co.edu.poligran.serviciosalestudiante.service.UsuarioService;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

@RestController
public class ReservaController extends BaseController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ReservaService reservaService;

	@RequestMapping(value = "/reservas/mis-reservas", method = RequestMethod.GET)
	public List<ReservaDTO> consultarMisReservas() throws UserNotFoundException {
		String usuarioEnSesion = getUsuarioEnSesion();
		UsuarioDTO usuarioDTO = usuarioService.findByUsername(usuarioEnSesion);
		return reservaService.consultarReservasVigentesPorUsuario(usuarioDTO);
	}
}
