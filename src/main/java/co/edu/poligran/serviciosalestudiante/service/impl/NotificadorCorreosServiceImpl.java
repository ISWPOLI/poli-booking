package co.edu.poligran.serviciosalestudiante.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poligran.serviciosalestudiante.service.NotificadorCorreosService;
import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.util.DateUtils;

import java.util.Locale;

@Service
@Transactional
public class NotificadorCorreosServiceImpl extends BaseService implements NotificadorCorreosService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void enviarTokenRestablecimientoContraseña(PasswordResetTokenDTO token, String contextPath) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            UsuarioDTO user = token.getUser();
            String tokenString = token.getToken();
            String url = contextPath + "/user/change-password?id=" +
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

    @Override
    public void enviarNotificacionReservaCancelada(ReservaDTO reservaCancelada) {
        try {
            MimeMessage correo = mailSender.createMimeMessage();

            String email = reservaCancelada.getUsuario().getEmail();

            MimeMessageHelper helper = new MimeMessageHelper(correo, true);
            helper.setTo(email);
            helper.setFrom(emailFrom);
            helper.setSubject("Reserva cancelada en Politécnico Grancolombiano");
            helper.setText(getTextoCorreoCancelacionReserva(reservaCancelada), true);

            mailSender.send(correo);

        } catch (MessagingException e) {
            logger.error("Error enviando correo de notificación de reserva cancelada");
        }
    }

    @Override
    public void enviarNotificacionReservaConfirmada(ReservaDTO reserva) {
        try {
            MimeMessage correo = mailSender.createMimeMessage();

            String email = reserva.getUsuario().getEmail();

            MimeMessageHelper helper = new MimeMessageHelper(correo, true);
            helper.setTo(email);
            helper.setFrom(emailFrom);
            helper.setSubject("Reserva confirmada en Politécnico Grancolombiano");
            helper.setText(getTextoCorreoConfirmacionReserva(reserva), true);
            mailSender.send(correo);

        } catch (MessagingException e) {
            logger.error("Error enviando correo de notificación de reserva confirmada");
        }
    }

    private String getTextoCorreoCancelacionReserva(ReservaDTO reserva) {
        Context context = new Context();
        context.setVariable("tipo", reserva.getBloque().getEspacio().getTipoEspacio());
        context.setVariable("nombre", reserva.getBloque().getEspacio().getNombre());
        context.setVariable("dia", DateUtils.format(reserva.getBloque().getDia(), "yyyy-MM-dd",
                new Locale("es", "co")));
        context.setVariable("inicio", DateUtils.format(reserva.getBloque().getTiempoInicio(), "HH:mm:SS",
                new Locale("es", "co")));
        context.setVariable("fin", DateUtils.format(reserva.getBloque().getTiempoFin(), "HH:mm:ss",
                new Locale("es", "co")));
        return templateEngine.process("cancelacion-reserva", context);
    }

    private String getTextoCorreoConfirmacionReserva(ReservaDTO reserva) {
        Context context = new Context();
        context.setVariable("tipo", reserva.getBloque().getEspacio().getTipoEspacio());
        context.setVariable("nombre", reserva.getBloque().getEspacio().getNombre());
        context.setVariable("dia", DateUtils.format(reserva.getBloque().getDia(), "yyyy-MM-dd",
                new Locale("es", "co")));
        context.setVariable("inicio", DateUtils.format(reserva.getBloque().getTiempoInicio(), "HH:mm:SS",
                new Locale("es", "co")));
        context.setVariable("fin", DateUtils.format(reserva.getBloque().getTiempoFin(), "HH:mm:ss",
                new Locale("es", "co")));
        return templateEngine.process("confirmacion-reserva", context);
    }
}
