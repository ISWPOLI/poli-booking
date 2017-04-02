package co.edu.poligran.serviciosalestudiante.repository;

import co.edu.poligran.serviciosalestudiante.entities.BloquePlantillaEntity;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloquePlantillaRepository extends JpaRepository<BloquePlantillaEntity, Long> {

    @Query("select b from BloquePlantillaEntity b where b.tipoEspacio = :tipoEspacio")
    List<BloquePlantillaEntity> consultarBloquesPlantillaPorTipoEspacio(@Param("tipoEspacio") TipoEspacioEntity tipoEspacio);
}
