package com.tailoy.inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.HistorialAccion;

@Repository
public interface HistorialAccionRepository extends JpaRepository<HistorialAccion, Integer> {
}
