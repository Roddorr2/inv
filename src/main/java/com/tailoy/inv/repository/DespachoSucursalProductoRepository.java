package com.tailoy.inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.DespachoSucursalProducto;

@Repository
public interface DespachoSucursalProductoRepository extends JpaRepository<DespachoSucursalProducto, Integer> {

}
