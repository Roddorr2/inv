package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.model.OrdenCompraProducto;

public interface OrdenCompraProductoService {
    void registrarProductosOrden(List<OrdenCompraProducto> productos);
    List<OrdenCompraProducto> obtenerProductosPorOrden(int ordenCompraId);
    void eliminarProductosOrden(int ordenCompraId);
    boolean validarStockParaOrden(List<OrdenCompraProducto> productos);
}
