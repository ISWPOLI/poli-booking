package co.edu.poligran.serviciosalestudiante.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poligran.serviciosalestudiante.entities.PasswordResetTokenEntity;
import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.entities.UsuarioEntity;
import co.edu.poligran.serviciosalestudiante.exception.InvalidPasswordResetTokenException;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.repository.PasswordResetTokenRepository;
import co.edu.poligran.serviciosalestudiante.repository.UserRepository;
import co.edu.poligran.serviciosalestudiante.service.RoleService;
import co.edu.poligran.serviciosalestudiante.service.UserService;
import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.RoleDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	public UsuarioDTO findByUsername(String username) throws UserNotFoundException {
		UsuarioEntity user = userRepository.findByUsername(username);

		if (user != null) {
			return mapper.map(user, UsuarioDTO.class);
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public UsuarioDTO create(UsuarioDTO userDTO, RoleTypeEnum roleType) throws UsernameIsNotUniqueException {
		processValidations(userDTO);

		setUserRole(roleType, userDTO);

		userDTO.setPassword(userDTO.getPassword());
		userDTO.setActive(true);

		UsuarioEntity userEntity = mapper.map(userDTO, UsuarioEntity.class);
		return mapper.map(userRepository.saveAndFlush(userEntity), UsuarioDTO.class);
	}

	private void processValidations(UsuarioDTO userBean) throws UsernameIsNotUniqueException {
		if (!isUsernameUnique(userBean.getId(), userBean.getUsername())) {
			throw new UsernameIsNotUniqueException();
		}
	}

	private void setUserRole(RoleTypeEnum roleType, UsuarioDTO userDTO) {
		RoleDTO role = roleService.findByType(roleType);
		userDTO.getRoles().add(role);
	}

	@Override
	public boolean isUsernameUnique(Long idUsuario, String username) {
		try {
			UsuarioDTO persistedUser = findByUsername(username);
			return persistedUser != null && persistedUser.getId().compareTo(idUsuario) != 0;
		} catch (UserNotFoundException e) {
			return true;
		}
	}

	@Override
	public boolean isUserCreated(String username) {
		return userRepository.isUserCreated(username);
	}

	@Override
	public UsuarioDTO findByEmail(String email) throws UserNotFoundException {
		UsuarioEntity user = userRepository.findByEmail(email);
		if (user != null) {
			return mapper.map(user, UsuarioDTO.class);
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public PasswordResetTokenDTO createPasswordResetTokenForUser(UsuarioDTO user) {
		PasswordResetTokenEntity tokenEntity = passwordResetTokenRepository
				.findByUser(mapper.map(user, UsuarioEntity.class));
		if (tokenEntity != null) {
			passwordResetTokenRepository.delete(tokenEntity.getId());
		}

		String token = UUID.randomUUID().toString();

		PasswordResetTokenDTO tokenDTO = new PasswordResetTokenDTO();
		tokenDTO.setUser(user);
		tokenDTO.setExpirationDate(calculateExpirationDateForToken());
		tokenDTO.setToken(token);

		tokenEntity = passwordResetTokenRepository.saveAndFlush(mapper.map(tokenDTO, PasswordResetTokenEntity.class));
		logger.info("generated token {} for user {}", tokenEntity.getToken(), user.getEmail());

		return mapper.map(tokenEntity, PasswordResetTokenDTO.class);
	}

	private Date calculateExpirationDateForToken() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, PasswordResetTokenDTO.EXPIRATION_TIME_IN_MINUTES);

		return calendar.getTime();
	}

	@Override
	public void authorizePasswordChange(long id, String token) throws InvalidPasswordResetTokenException {
		PasswordResetTokenEntity passToken = passwordResetTokenRepository.findByToken(token);
		validateToken(id, passToken);

		UsuarioEntity user = passToken.getUser();
		Authentication auth = new UsernamePasswordAuthenticationToken(
				userDetailsService.getSpringSecurityUser(mapper.map(user, UsuarioDTO.class)), null,
				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	public void validatePasswordResetToken(long id, String token) throws InvalidPasswordResetTokenException {
		PasswordResetTokenEntity passToken = passwordResetTokenRepository.findByToken(token);
		validateToken(id, passToken);
	}

	private PasswordResetTokenEntity validateToken(long id, PasswordResetTokenEntity passToken)
			throws InvalidPasswordResetTokenException {
		if ((passToken == null) || (passToken.getUser().getId() != id)) {
			throw new InvalidPasswordResetTokenException();
		}

		Calendar cal = Calendar.getInstance();
		if ((passToken.getExpirationDate().getTime() - cal.getTime().getTime()) <= 0) {
			throw new InvalidPasswordResetTokenException();
		}

		return passToken;
	}

	@Override
	public void changeUserPassword(String newPassword) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UsuarioEntity userEntity = userRepository.findByUsername(user.getUsername());

		userEntity.setPassword(passwordEncoder.encode(newPassword));
		userRepository.saveAndFlush(userEntity);

		deletePasswordResetTokenForUser(mapper.map(userEntity, UsuarioDTO.class));
		logger.info("password for user {} successfully changed", user.getUsername());
	}

	@Override
	public void deletePasswordResetTokenForUser(UsuarioDTO user) {
		PasswordResetTokenEntity tokenEntity = passwordResetTokenRepository
				.findByUser(mapper.map(user, UsuarioEntity.class));
		if (tokenEntity != null) {
			passwordResetTokenRepository.delete(tokenEntity.getId());
		}
	}
}
