package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.dto.ProductoDespachoDTO;
import com.tailoy.inv.model.DespachoSucursalProducto;

public interface DespachoSucursalProductoService {
    void registrarProductosDespacho(List<ProductoDespachoDTO> productosDespacho, int despachoId);
    List<DespachoSucursalProducto> obtenerProductosPorDespacho(int despachoId);
    void eliminarProductoDespacho(int despachoId);
    boolean validarStockParaDespacho(List<ProductoDespachoDTO> productosDespacho);
}
