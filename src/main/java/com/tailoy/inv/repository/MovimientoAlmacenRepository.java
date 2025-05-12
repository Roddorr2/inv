package com.tailoy.inv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.MovimientoAlmacen;

@Repository
public interface MovimientoAlmacenRepository extends JpaRepository<MovimientoAlmacen, Integer> {
    List<MovimientoAlmacen> findByTipoAlmacen(int tipoAlmacen);
}
