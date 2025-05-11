package com.tailoy.inv.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tailoy.inv.dto.DespachoSucursalDTO;
import com.tailoy.inv.model.DespachoSucursal;

public interface DespachoSucursalService {
    DespachoSucursal registrarDespacho(DespachoSucursalDTO despachoDTO);
    List<DespachoSucursal> listarDespachos();
    DespachoSucursal obtenerPorId(int id);
    List<DespachoSucursal> listarPorEstado(int estadoOperacion);
    List<DespachoSucursal> buscarPorFechas(LocalDateTime desde, LocalDateTime hasta);
    void actualizarEstado(int ordenId, int nuevoEstado);
    DespachoSucursal obtenerDespachoPorId(int despachoId, int nuevoEstado);
    byte[] exportarDespacho(int despachoId, String formato);
}
