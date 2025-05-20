package com.tailoy.inv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	Optional<Producto> findByCodigo(int codigo);
	Optional<Producto> findByMarca(String marca);
	List<Producto> findByEstadoTrue();
	List<Producto> findByNombre(String nombre);
	boolean existsByCodigo(int codigo);
}
