package co.edu.poligran.serviciosalestudiante.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poligran.serviciosalestudiante.service.MailSenderService;
import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UserDTO;

@Service
@Transactional
public class MailSenderServiceImpl extends BaseService implements MailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String emailFrom;

	@Override
	public void sendPasswordResetTokenEmail(PasswordResetTokenDTO token, String contextPath) {
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			UserDTO user = token.getUser();
			String tokenString = token.getToken();
			String url = contextPath + "/user/changePassword?id=" + 
				      user.getId() + "&token=" + tokenString;
			
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(user.getEmail());
			helper.setFrom(emailFrom);
			helper.setSubject("Restablecer contraseña");
			helper.setText("Para restablecer tu contraseña haz clic en el siguiente enlace:\n" + url);
			
			mailSender.send(mail);
			logger.info("password reset email sent to user: {}", user.getEmail());
		} catch (MessagingException e) {
			logger.error("Error sending reset password email", e);
		}
	}
}
