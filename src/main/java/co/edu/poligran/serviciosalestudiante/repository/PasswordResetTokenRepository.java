package co.edu.poligran.serviciosalestudiante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.poligran.serviciosalestudiante.entities.PasswordResetTokenEntity;
import co.edu.poligran.serviciosalestudiante.entities.UsuarioEntity;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {
	PasswordResetTokenEntity findByUser(UsuarioEntity user);
	PasswordResetTokenEntity findByToken(String token);
}
