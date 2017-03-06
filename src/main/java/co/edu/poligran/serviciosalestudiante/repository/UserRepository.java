package co.edu.poligran.serviciosalestudiante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import co.edu.poligran.serviciosalestudiante.entities.UsuarioEntity;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<UsuarioEntity, Long> {
	UsuarioEntity findByUsername(@Param("username") String username);

	UsuarioEntity findByEmail(@Param("email") String email);

	@Query("select count(u) > 0 from UsuarioEntity u where u.username = :username")
	boolean isUserCreated(@Param("username") String username);
}
