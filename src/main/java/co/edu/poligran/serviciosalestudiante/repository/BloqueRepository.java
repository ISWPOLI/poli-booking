package co.edu.poligran.serviciosalestudiante.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import co.edu.poligran.serviciosalestudiante.entities.BloqueEntity;

@RepositoryRestResource(collectionResourceRel = "bloques", path = "bloques")
public interface BloqueRepository extends JpaRepository<BloqueEntity, Long> {

	@Query("select b from BloqueEntity b where b.dia = :dia and b.espacio.id = :idEspacio "
			+ "and b.tiempoInicio > current_timestamp() and b.reservas IS EMPTY order by b.tiempoInicio")
	List<BloqueEntity> consultarBloquesVigentesPorDiaYEspacio(@DateTimeFormat(iso = ISO.DATE) @Param("dia") Date dia,
			@Param("idEspacio") Long idEspacio);

	@Query("select b from BloqueEntity b where b.tiempoInicio > current_timestamp() and b.reservas IS EMPTY order by b.tiempoInicio")
	List<BloqueEntity> consultarBloquesVigentes();

}
