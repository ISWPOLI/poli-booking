package co.edu.poligran.serviciosalestudiante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import co.edu.poligran.serviciosalestudiante.entities.UserEntity;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);

	@Query("select count(u) > 0 from UserEntity u where u.username = :username")
	boolean isUserCreated(@Param("username") String username);
}
