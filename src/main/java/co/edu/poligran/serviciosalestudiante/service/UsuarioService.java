package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.exception.InvalidPasswordResetTokenException;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO buscarPorUsername(String username) throws UserNotFoundException;

    UsuarioDTO buscarPorCorreo(String email) throws UserNotFoundException;

    UsuarioDTO crear(UsuarioDTO user, RoleTypeEnum roleType) throws UsernameIsNotUniqueException;

    boolean esUsernameUnico(Long idUsuario, String username);

    boolean existeUsuario(String username);

    PasswordResetTokenDTO crearTokenRestablecerPassword(UsuarioDTO user);

    void eliminarTokenRestablecerPassword(UsuarioDTO user);

    void validarTokenRestablecerPassword(long userId, String token) throws InvalidPasswordResetTokenException;

    void autorizarCambioPassword(long userId, String token) throws InvalidPasswordResetTokenException;

    void cambiarPassword(String newPassword);
}
