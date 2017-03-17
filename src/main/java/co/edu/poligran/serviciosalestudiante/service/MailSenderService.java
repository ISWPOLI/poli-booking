package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;

public interface MailSenderService {

    void enviarTokenRestablecimientoContraseña(PasswordResetTokenDTO token, String contextPath);

    void enviarNotificacionReservaCancelada(ReservaDTO reservaCancelada);
}
