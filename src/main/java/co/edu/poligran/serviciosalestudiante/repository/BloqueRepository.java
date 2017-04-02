package co.edu.poligran.serviciosalestudiante.repository;

import co.edu.poligran.serviciosalestudiante.entities.BloqueEntity;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "bloques", path = "bloques")
public interface BloqueRepository extends JpaRepository<BloqueEntity, Long> {

    @Query("select b from BloqueEntity b where b.dia = :dia and b.espacio.tipoEspacio = :tipoEspacio "
            + "and b.tiempoInicio > current_timestamp() and b.reservas IS EMPTY order by b.tiempoInicio")
    List<BloqueEntity> consultarBloquesVigentesPorDiaYTipoEspacio(@DateTimeFormat(iso = ISO.DATE) @Param("dia") Date dia,
                                                                  @Param("tipoEspacio") TipoEspacioEntity tipoEspacio);

    @Query("select b from BloqueEntity b where b.espacio.tipoEspacio = :tipoEspacio "
            + "and b.tiempoInicio > current_timestamp() and b.reservas IS EMPTY order by b.tiempoInicio")
    List<BloqueEntity> consultarBloquesVigentesPorTipoEspacio(@Param("tipoEspacio") TipoEspacioEntity tipoEspacio);

    @Query("select b from BloqueEntity b where b.tiempoInicio > current_timestamp() and b.reservas IS EMPTY order by b.tiempoInicio")
    List<BloqueEntity> consultarBloquesVigentes();

}
