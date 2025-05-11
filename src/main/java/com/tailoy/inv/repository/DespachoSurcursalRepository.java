package com.tailoy.inv.repository;

import com.tailoy.inv.model.DespachoSucursal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespachoSurcursalRepository extends JpaRepository<DespachoSucursal, Integer> {
    List<DespachoSucursal> findByEstadoOperacion(int estadoOperacion);
    List<DespachoSucursal> findByFechaDespachoBetween(LocalDateTime desde, LocalDateTime hasta);
    Optional<DespachoSucursal> findByIdAndEstadoOperacion(int id, int estadoOperacion);
}
