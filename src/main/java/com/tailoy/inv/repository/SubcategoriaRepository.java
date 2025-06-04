package com.tailoy.inv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.dto.SubcategoriaDTO;
import com.tailoy.inv.model.Subcategoria;
import com.tailoy.inv.model.Categoria;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Integer> {
	boolean existsByNombre(String nombre);
	List<SubcategoriaDTO> findByNombreContainingIgnoreCase(String nombre);
	List<SubcategoriaDTO> findByCategoria(Categoria categoria);
}
