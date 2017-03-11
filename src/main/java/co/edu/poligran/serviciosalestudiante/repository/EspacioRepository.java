package co.edu.poligran.serviciosalestudiante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.poligran.serviciosalestudiante.entities.EspacioEntity;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacio;

public interface EspacioRepository extends JpaRepository<EspacioEntity, Long> {
	List<EspacioEntity> findByTipoEspacio(TipoEspacio tipoEspacio);
}
