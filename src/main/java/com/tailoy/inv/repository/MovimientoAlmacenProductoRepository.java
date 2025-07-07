package com.tailoy.inv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.MovimientoAlmacenProducto;

@Repository
public interface MovimientoAlmacenProductoRepository extends JpaRepository<MovimientoAlmacenProducto, Integer> {
    List<MovimientoAlmacenProducto> findByMovimientoAlmacenId(int movimientoAlmacenId);
    List<MovimientoAlmacenProducto> findByMovimientoAlmacenIdAndCantidadGreaterThan(int movimientoAlmacenId, int cantidad);
}
