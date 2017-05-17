package co.edu.poligran.serviciosalestudiante.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
	public static final String FORMATO_FECHA_POR_DEFECTO = "yyyy-MM-dd";

	protected String getUsuarioEnSesion() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

    protected String getFullAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
