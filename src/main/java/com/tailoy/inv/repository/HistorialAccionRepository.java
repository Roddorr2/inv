package com.tailoy.inv.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.HistorialAccion;

@Repository
public interface HistorialAccionRepository extends JpaRepository<HistorialAccion, Integer> {
    List<HistorialAccion> findByUsuarioNombreAndTipoAccion(String nombreUsuario, int tipoAccion);
    List<HistorialAccion> findByUsuarioNombreAndTipoAccionAndFechaBetweenAndModulo(String nombreUsuario, int tipoAccion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int modulo);
}
