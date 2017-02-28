package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.exception.InvalidPasswordResetTokenException;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UserDTO;

public interface UserService {
	UserDTO findByUsername(String username) throws UserNotFoundException;

	UserDTO findByEmail(String email) throws UserNotFoundException;

	UserDTO create(UserDTO user, RoleTypeEnum roleType) throws UsernameIsNotUniqueException;

	boolean isUsernameUnique(Long idUsuario, String username);

	boolean isUserCreated(String username);

	PasswordResetTokenDTO createPasswordResetTokenForUser(UserDTO user);

	void deletePasswordResetTokenForUser(UserDTO user);

	void validatePasswordResetToken(long userId, String token) throws InvalidPasswordResetTokenException;

	void authorizePasswordChange(long userId, String token) throws InvalidPasswordResetTokenException;

	void changeUserPassword(String newPassword);
}
