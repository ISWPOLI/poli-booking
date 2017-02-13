package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;

public interface MailSenderService {

	void sendPasswordResetTokenEmail(PasswordResetTokenDTO token, String contextPath);
}
