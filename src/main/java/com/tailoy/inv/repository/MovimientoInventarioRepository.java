package com.tailoy.inv.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.MovimientoInventario;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
    List<MovimientoInventario> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<MovimientoInventario> findByUsuarioId(int usuarioId);
    List<MovimientoInventario> findByProductoId(int productoId);
}

