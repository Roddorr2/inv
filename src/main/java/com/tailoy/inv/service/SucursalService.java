package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.dto.SucursalDTO;

public interface SucursalService {
    SucursalDTO registrarSucursal(SucursalDTO sucursalDTO);
    List<SucursalDTO> listarSucursales();
    SucursalDTO obtenerSucursalPorId(int id);
    List<SucursalDTO> obtenerSucursalPorCorreo(String correo);
    SucursalDTO actualizarSucursal(int id, SucursalDTO sucursalDTO);
    List<SucursalDTO> buscarPorDireccionOCorreo(String q);
    boolean existeSucursalPorCorreo(String correo);
}
