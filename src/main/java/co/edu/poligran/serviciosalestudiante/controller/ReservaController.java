package co.edu.poligran.serviciosalestudiante.controller;

import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.service.ServiciosFacade;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ReservaController extends BaseController {

    public static final String RESERVAS_ROOT_URL = "/reservas/mis-reservas";

    @Autowired
    private ServiciosFacade serviciosFacade;

    @RequestMapping(value = RESERVAS_ROOT_URL, method = RequestMethod.GET)
    public List<ReservaDTO> consultarMisReservas() throws UserNotFoundException {
        String usuarioEnSesion = getUsuarioEnSesion();
        return serviciosFacade.consultarReservasVigentesPorUsuario(usuarioEnSesion);
    }

    @RequestMapping(value = RESERVAS_ROOT_URL + "/reservas-grafica", method = RequestMethod.GET)
    public List<ReservaDTO> consutarReservasGrafica() throws Exception {
        return serviciosFacade.consultarReservasVigentesGrafica();
    }

    @RequestMapping(value = RESERVAS_ROOT_URL + "/historico-grafica", method = RequestMethod.GET)
    public List<ReservaDTO> consutarHistorico() throws Exception {
        return serviciosFacade.consultarHistorico();
    }

    @RequestMapping(value = RESERVAS_ROOT_URL + "/{id}", method = RequestMethod.DELETE)
    public ReservaDTO eliminarReserva(@PathVariable(name = "id") Long idReserva) {
        return serviciosFacade.eliminarReserva(idReserva, getUsuarioEnSesion());
    }

    @RequestMapping(value = RESERVAS_ROOT_URL, method = RequestMethod.POST)
    public void confirmarReserva(HttpServletRequest request, @RequestParam("idBloque") Long idBloque) throws
            UserNotFoundException {
        serviciosFacade.confirmarReserva(idBloque, getUsuarioEnSesion());
    }

}
