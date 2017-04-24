package co.edu.poligran.serviciosalestudiante.repository;

import co.edu.poligran.serviciosalestudiante.entities.EspacioEntity;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspacioRepository extends JpaRepository<EspacioEntity, Long> {
    List<EspacioEntity> findByTipoEspacio(TipoEspacioEntity tipoEspacio);

    long countByNombreAndTipoEspacio(String nombre, TipoEspacioEntity tipoEspacio);
}
