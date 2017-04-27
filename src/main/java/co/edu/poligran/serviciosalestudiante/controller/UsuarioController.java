package co.edu.poligran.serviciosalestudiante.controller;

import co.edu.poligran.serviciosalestudiante.exception.InvalidPasswordResetTokenException;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.service.ServiciosFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
public class UsuarioController extends BaseController {

    @Autowired
    private ServiciosFacade serviciosFacade;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/user/reset-password", method = RequestMethod.POST)
    public String restablecerPassword(HttpServletRequest request, @RequestParam("email") String email)
            throws UserNotFoundException {
        return serviciosFacade.resetPassword(email, getFullAppUrl(request));
    }

    @RequestMapping(value = "/user/change-password", method = RequestMethod.GET)
    public void mostrarPaginaCambioPassword(@RequestParam("id") long id, @RequestParam("token") String token,
                                            HttpServletResponse response) throws InvalidPasswordResetTokenException,
            IOException {
        serviciosFacade.autorizarCambioPassword(id, token);
        response.sendRedirect("/#/password-change");
    }

    @RequestMapping(value = "/user/save-password", method = RequestMethod.POST)
    public String cambiarPassword(@RequestParam("new-password") String nuevaPassword) {
        serviciosFacade.cambiarPassword(nuevaPassword);
        return "La contraseña se ha cambiado exitosamente";
    }
}
