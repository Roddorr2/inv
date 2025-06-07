package com.tailoy.inv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer>{
    boolean existsByNombre(String nombre);
    List<Cargo> findByNombreContainingIgnoreCase(String nombre);
}
