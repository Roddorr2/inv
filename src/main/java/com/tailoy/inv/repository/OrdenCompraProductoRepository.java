package com.tailoy.inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.OrdenCompraProducto;

@Repository
public interface OrdenCompraProductoRepository extends JpaRepository<OrdenCompraProducto, Integer> {

}
