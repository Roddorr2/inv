package com.tailoy.inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
}
