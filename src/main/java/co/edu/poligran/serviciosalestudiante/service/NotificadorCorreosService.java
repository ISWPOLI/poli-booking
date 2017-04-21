package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;

public interface NotificadorCorreosService {

    void enviarTokenRestablecimientoContraseña(PasswordResetTokenDTO token, String contextPath);

    void enviarNotificacionReservaCancelada(ReservaDTO reservaCancelada);

    void enviarNotificacionReservaCanceladaPorAdmin(ReservaDTO reservaCancelada);

    void enviarNotificacionReservaConfirmada(ReservaDTO reserva);
}
