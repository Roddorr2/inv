package com.tailoy.inv.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.OrdenCompra;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Integer> {
	List<OrdenCompra> findByEstadoOperacion(int estadoOperacion);
	List<OrdenCompra> findByFechaOrdenBetween(LocalDateTime desde, LocalDateTime hasta);
}
