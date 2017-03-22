package co.edu.poligran.serviciosalestudiante.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.service.BloqueService;
import co.edu.poligran.serviciosalestudiante.service.NotificadorCorreosService;
import co.edu.poligran.serviciosalestudiante.service.ReservaService;
import co.edu.poligran.serviciosalestudiante.service.UsuarioService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

@RestController
public class ReservaController extends BaseController {

    public static final String RESERVAS_ROOT_URL = "/reservas/mis-reservas";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private NotificadorCorreosService notificadorCorreosService;

    @Autowired
    private BloqueService bloquesService;

    @RequestMapping(value = RESERVAS_ROOT_URL, method = RequestMethod.GET)
    public List<ReservaDTO> consultarMisReservas() throws UserNotFoundException {
        String usuarioEnSesion = getUsuarioEnSesion();
        UsuarioDTO usuarioDTO = usuarioService.findByUsername(usuarioEnSesion);
        return reservaService.consultarReservasVigentesPorUsuario(usuarioDTO);
    }

    @RequestMapping(value = RESERVAS_ROOT_URL + "/{id}", method = RequestMethod.DELETE)
    public ReservaDTO cancelarReserva(@PathVariable(name = "id") Long idReserva) {
        ReservaDTO reserva = reservaService.consultarReserva(idReserva);

        if (reserva.getUsuario().getUsername().equals(getUsuarioEnSesion())) {
            reservaService.cancelarReserva(reserva.getId());
            notificadorCorreosService.enviarNotificacionReservaCancelada(reserva);
        } else {
            logger.error("error de seguridad. se está intentando cancelar una reserva de un usuario diferente");
        }

        return reserva;
    }

    @RequestMapping(value = RESERVAS_ROOT_URL, method = RequestMethod.POST)
    public void confirmarReserva(HttpServletRequest request, @RequestParam("idBloque") Long idBloque) throws UserNotFoundException {
        String username = getUsuarioEnSesion();
        UsuarioDTO usuario = usuarioService.findByUsername(username);
        BloqueDTO bloque = bloquesService.consultarBloque(idBloque);

        ReservaDTO reserva = reservaService.crearReserva(usuario, bloque);
        notificadorCorreosService.enviarNotificacionReservaConfirmada(reserva);
    }
}
