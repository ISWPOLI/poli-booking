package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.exception.InvalidPasswordResetTokenException;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

public interface UsuarioService {
	UsuarioDTO findByUsername(String username) throws UserNotFoundException;

	UsuarioDTO findByEmail(String email) throws UserNotFoundException;

	UsuarioDTO create(UsuarioDTO user, RoleTypeEnum roleType) throws UsernameIsNotUniqueException;

	boolean isUsernameUnique(Long idUsuario, String username);

	boolean isUserCreated(String username);

	PasswordResetTokenDTO createPasswordResetTokenForUser(UsuarioDTO user);

	void deletePasswordResetTokenForUser(UsuarioDTO user);

	void validatePasswordResetToken(long userId, String token) throws InvalidPasswordResetTokenException;

	void authorizePasswordChange(long userId, String token) throws InvalidPasswordResetTokenException;

	void changeUserPassword(String newPassword);
}
