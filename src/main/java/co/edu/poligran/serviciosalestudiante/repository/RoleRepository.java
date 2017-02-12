package co.edu.poligran.serviciosalestudiante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import co.edu.poligran.serviciosalestudiante.entities.RoleEntity;
import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;

@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findByType(RoleTypeEnum type);
}
