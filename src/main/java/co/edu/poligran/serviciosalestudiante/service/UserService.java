package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.service.dto.UserDTO;

public interface UserService {
	UserDTO getByUsername(String username) throws UserNotFoundException;
	UserDTO create(UserDTO user, RoleTypeEnum roleType) throws UsernameIsNotUniqueException;
	boolean isUsernameUnique(Long idUsuario, String username);
	boolean isUserCreated(String username);

}
