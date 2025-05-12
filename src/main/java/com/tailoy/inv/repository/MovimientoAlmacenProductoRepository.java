package com.tailoy.inv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tailoy.inv.model.MovimientoAlmacenProducto;

public interface MovimientoAlmacenProductoRepository extends JpaRepository<MovimientoAlmacenProducto, Integer> {
    List<MovimientoAlmacenProducto> findByMovimientoAlmacenId(int movimientoAlmacenId);
    List<MovimientoAlmacenProducto> findByMovimientoAlmacenIdAndCantidadGreaterThan(int movimientoAlmacenId, int cantidad);
}
