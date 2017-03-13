package co.edu.poligran.serviciosalestudiante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.poligran.serviciosalestudiante.entities.ReservaEntity;
import co.edu.poligran.serviciosalestudiante.entities.UsuarioEntity;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    @Query("select r from ReservaEntity r where r.usuario = :usuario and r.bloque.tiempoInicio > current_timestamp() order by r.bloque.tiempoInicio")
    List<ReservaEntity> consultarReservasVigentesPorUsuario(@Param("usuario") UsuarioEntity usuario);
}
