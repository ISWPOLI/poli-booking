package co.edu.poligran.serviciosalestudiante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import co.edu.poligran.serviciosalestudiante.entities.BloqueEntity;

@RepositoryRestResource(collectionResourceRel = "bloques", path = "bloques")
public interface BloqueRepository extends JpaRepository<BloqueEntity, Long> {

}
