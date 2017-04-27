package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.beans.DiaCalendarioBean;
import co.edu.poligran.serviciosalestudiante.exception.InvalidPasswordResetTokenException;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;

import java.util.Date;
import java.util.List;

public interface ServiciosFacade {
    List<BloquePlantillaDTO> consultarBloquesPlantillaPorTipoEspacio(String tipoEspacio);

    List<BloqueDTO> consultarBloquesVigentesPorDiaYTipoEspacio(Date dia, String tipoEspacio);

    List<DiaCalendarioBean> consultarDiasConBloquesDisponibles(String tipoEspacio);

    BloqueDTO consultarBloque(Long idBloque);

    List<BloqueDTO> generarBloquesMasivamente(String tipoEspacio, Date fechaInicio, Date fechaFin);

    void eliminarBloquesMasivamente(String tipoEspacio, Date fechaInicio, Date fechaFin);

    List<ReservaDTO> consultarReservasVigentesPorUsuario(String usuario) throws UserNotFoundException;

    List<ReservaDTO> consultarReservasVigentesGrafica();

    List<ReservaDTO> consultarHistorico();

    ReservaDTO eliminarReserva(Long idReserva, String usuarioEnSesion);

    void confirmarReserva(Long idBloque, String usuarioEnSesion) throws UserNotFoundException;

    String resetPassword(String email, String urlCompleta) throws UserNotFoundException;

    void autorizarCambioPassword(long idUsuario, String token) throws InvalidPasswordResetTokenException;

    void cambiarPassword(String nuevaPassword);
}
