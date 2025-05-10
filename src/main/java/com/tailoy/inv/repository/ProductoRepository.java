package com.tailoy.inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
