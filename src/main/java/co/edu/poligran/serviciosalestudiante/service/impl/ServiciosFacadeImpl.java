package co.edu.poligran.serviciosalestudiante.service.impl;

import co.edu.poligran.serviciosalestudiante.beans.DiaCalendarioBean;
import co.edu.poligran.serviciosalestudiante.exception.InvalidPasswordResetTokenException;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.service.*;
import co.edu.poligran.serviciosalestudiante.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServiciosFacadeImpl implements ServiciosFacade {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BloquePlantillaService bloquePlantillaService;
    @Autowired
    private BloqueService bloquesService;
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private NotificadorCorreosService notificadorCorreosService;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<BloquePlantillaDTO> consultarBloquesPlantillaPorTipoEspacio(String tipoEspacio) {
        return bloquePlantillaService.consultarBloquesPlantillaPorTipoEspacio(tipoEspacio);
    }

    @Override
    public List<BloqueDTO> consultarBloquesVigentesPorDiaYTipoEspacio(Date dia, String tipoEspacio) {
        return bloquesService.consultarBloquesVigentesPorDiaYTipoEspacio(dia, tipoEspacio);
    }

    @Override
    public List<DiaCalendarioBean> consultarDiasConBloquesDisponibles(String tipoEspacio) {
        return bloquesService.consultarDiasConBloquesDisponibles(tipoEspacio);
    }

    @Override
    public BloqueDTO consultarBloque(Long idBloque) {
        return bloquesService.consultarBloque(idBloque);
    }

    @Override
    public List<BloqueDTO> generarBloquesMasivamente(String tipoEspacio, Date fechaInicio, Date fechaFin) {
        return bloquesService.generarBloquesMasivamente(tipoEspacio, fechaInicio, fechaFin);
    }

    @Override
    public void eliminarBloquesMasivamente(String tipoEspacio, Date fechaInicio, Date fechaFin) {
        bloquesService.eliminarBloquesMasivamente(tipoEspacio, fechaInicio, fechaFin);
    }

    @Override
    public List<ReservaDTO> consultarReservasVigentesPorUsuario(String usuario) throws UserNotFoundException {
        return reservaService.consultarReservasVigentesPorUsuario(usuario);
    }

    @Override
    public List<ReservaDTO> consultarReservasVigentesGrafica() {
        return reservaService.consultarReservasVigentesGrafica();
    }

    @Override
    public List<ReservaDTO> consultarHistorico() {
        return reservaService.consultarHistorico();
    }

    @Override
    public ReservaDTO eliminarReserva(Long idReserva, String usuarioEnSesion) {
        ReservaDTO reserva = reservaService.consultarReserva(idReserva);
        if (reserva.getUsuario().getUsername().equals(usuarioEnSesion)) {
            reservaService.eliminarReserva(reserva.getId());
            notificadorCorreosService.enviarNotificacionReservaCancelada(reserva);
        } else {
            logger.error("error de seguridad. se está intentando cancelar una reserva de un usuario diferente");
        }
        return reserva;
    }

    @Override
    public void confirmarReserva(Long idBloque, String usuarioEnSesion) throws UserNotFoundException {
        UsuarioDTO usuario = usuarioService.buscarPorUsername(usuarioEnSesion);
        BloqueDTO bloque = bloquesService.consultarBloque(idBloque);

        ReservaDTO reserva = reservaService.crearReserva(usuario, bloque);
        notificadorCorreosService.enviarNotificacionReservaConfirmada(reserva);
    }

    @Override
    public String resetPassword(String email, String urlCompleta) throws UserNotFoundException {
        UsuarioDTO user = usuarioService.buscarPorCorreo(email);

        PasswordResetTokenDTO token = usuarioService.crearTokenRestablecerPassword(user);

        notificadorCorreosService.enviarTokenRestablecimientoContraseña(token, urlCompleta);

        return "El enlace para cambiar la contraseña se ha enviado correctamente al correo suministrado";
    }

    @Override
    public void autorizarCambioPassword(long idUsuario, String token) throws InvalidPasswordResetTokenException {
        usuarioService.autorizarCambioPassword(idUsuario, token);
    }

    @Override
    public void cambiarPassword(String nuevaPassword) {
        usuarioService.cambiarPassword(nuevaPassword);
    }
}
