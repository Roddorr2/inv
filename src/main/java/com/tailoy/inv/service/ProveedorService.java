package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.dto.ProveedorDTO;
import com.tailoy.inv.model.Proveedor;

public interface ProveedorService {
    Proveedor registrarProveedor(ProveedorDTO proveedorDTO);
    List<Proveedor> listarProveedores();
    Proveedor obtenerPorId(int id);
    Proveedor obtenerPorRuc(String ruc);
    Proveedor modificarProveedor(int idProveedor, ProveedorDTO proveedorDTO);
    void cambiarEstadoProveedor(int idProveedor, boolean nuevoEstado);
    List<Proveedor> buscarPorNombreORucOTelefonoODireccion(String q);
    boolean existeProveedoroPorRuc(String ruc);
    boolean existeProveedorPorNombre(String nombre);
}
