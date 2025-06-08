package com.tailoy.inv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
	Optional<Proveedor> findByRuc(String ruc);
	boolean existsByRuc(String ruc);
	boolean existsByNombre(String nombre);
	@Query("SELECT p FROM Proveedor p WHERE " +
			"LOWER(p.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
			"OR p.ruc LIKE CONCAT('%', :term, '%') " +
			"OR p.telefono LIKE CONCAT('%', :term, '%') " +
			"OR LOWER(p.direccion) LIKE LOWER(CONCAT('%', :term, '%')) ")
	List<Proveedor> findByNombreOrRucOrTelefonoOrDireccionContainingIgnoreCase(@Param("term") String term);

}
