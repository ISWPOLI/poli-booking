package co.edu.poligran.serviciosalestudiante.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.service.UsuarioService;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("authenticating user: {}", username);

		try {
			UsuarioDTO user = usuarioService.findByUsername(username);
			logger.info("user authenticated: {}", username);
			return getSpringSecurityUser(user);
		} catch (UserNotFoundException e) {
			logger.error("failed to authenticate user: {}", username);
			throw new UsernameNotFoundException(e.getMessage());
		}

	}

	private List<GrantedAuthority> getGrantedAuhtorities(UsuarioDTO userDTO) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		userDTO.getRoles().stream().map((role) -> {
			return role;
		}).forEach((role) -> {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType()));
		});
		logger.info("roles: {}", grantedAuthorities);
		return grantedAuthorities;
	}

	public User getSpringSecurityUser(UsuarioDTO user) {
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, getGrantedAuhtorities(user));
	}

}
