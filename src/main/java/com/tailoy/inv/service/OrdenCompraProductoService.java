package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.dto.ProductoOrdenDTO;
import com.tailoy.inv.model.OrdenCompraProducto;

public interface OrdenCompraProductoService {
    void registrarProductosOrden(List<ProductoOrdenDTO> productosOrden, int ordenCompraId);
    List<OrdenCompraProducto> obtenerProductosPorOrden(int ordenCompraId);
    void eliminarProductosOrden(int ordenCompraId);
    boolean validarStockParaOrden(List<ProductoOrdenDTO> productosOrden);
}
