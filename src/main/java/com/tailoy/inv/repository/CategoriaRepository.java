package com.tailoy.inv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.dto.CategoriaDTO;
import com.tailoy.inv.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    boolean existsByNombre(String nombre);
    List<CategoriaDTO> findByNombreContainingIgnoreCase(String nombre);
}
