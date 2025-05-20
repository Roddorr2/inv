package com.tailoy.inv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
	Optional<Proveedor> findByRuc(String ruc);
	boolean existsByRuc(String ruc);
}
