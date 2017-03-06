package co.edu.poligran.serviciosalestudiante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import co.edu.poligran.serviciosalestudiante.entities.CubiculoEntity;

@RepositoryRestResource(collectionResourceRel = "cubiculos", path = "cubiculos")
public interface CubiculoRepository extends JpaRepository<CubiculoEntity, Long> {

}
