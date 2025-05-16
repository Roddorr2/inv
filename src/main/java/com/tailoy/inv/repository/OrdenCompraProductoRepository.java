package com.tailoy.inv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.OrdenCompraProducto;

@Repository
public interface OrdenCompraProductoRepository extends JpaRepository<OrdenCompraProducto, Integer> {
    List<OrdenCompraProducto> findByOrdenCompraId(int ordenCompraId);
    void deleteByOrdenCompraId(int ordenCompraId);
}
