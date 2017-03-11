package co.edu.poligran.serviciosalestudiante.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected String getUsuarioEnSesion() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
}
