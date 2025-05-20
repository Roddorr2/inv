package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;

public interface ProductoService {
    Producto registrarProducto(ProductoDTO productoDTO);
    Producto modificarProducto(int idProducto, ProductoDTO productoDTO);
    void cambiarEstadoProducto(int idProducto, boolean nuevoEstado);
    Producto obtenerPorId(int id);
    Producto obtenerPorCodigo(int codigo);
    Producto obtenerPorMarca(String marca);
    List<Producto> listarProductos();
    List<Producto> listarActivos(); 
    List<Producto> buscarPorNombre(String nombre);
    boolean existeCodigoProducto(int idProducto);
}
