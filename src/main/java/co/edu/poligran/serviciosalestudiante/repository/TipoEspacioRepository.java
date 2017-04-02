package co.edu.poligran.serviciosalestudiante.repository;

import co.edu.poligran.serviciosalestudiante.entities.TipoEspacioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEspacioRepository extends JpaRepository<TipoEspacioEntity, Long> {

    TipoEspacioEntity findByNombre(String nombre);
}
