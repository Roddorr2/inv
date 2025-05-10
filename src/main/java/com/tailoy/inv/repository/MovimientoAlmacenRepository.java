package com.tailoy.inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.MovimientoAlmacen;

@Repository
public interface MovimientoAlmacenRepository extends JpaRepository<MovimientoAlmacen, Integer> {
}
