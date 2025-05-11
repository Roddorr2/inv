package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;

public interface ProductoService {
    Producto registrarProducto(ProductoDTO productoDTO);
    Producto modificarProducto(int idProducto, ProductoDTO productoDTO);
    void cambiarEstadoProducto(int idProducto, boolean nuevoEstado, int idUsuario);
    Producto obtenerPorId(int id);
    List<Producto> listarProductos();
    List<Producto> listarActivos(); 
    List<Producto> buscarPorNombreOCodigo(String texto);
    boolean existeCodigoProducto(int idProducto);
}
