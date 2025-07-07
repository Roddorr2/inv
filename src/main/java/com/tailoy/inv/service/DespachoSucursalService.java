package com.tailoy.inv.service;

import java.time.LocalDate;
import java.util.List;

import com.tailoy.inv.dto.DespachoSucursalDTO;
import com.tailoy.inv.dto.DespachoSucursalDetalladoDTO;
import com.tailoy.inv.model.DespachoSucursal;

public interface DespachoSucursalService {
    DespachoSucursal registrarDespacho(DespachoSucursalDTO despachoDTO);
    DespachoSucursal actualizarDespacho(DespachoSucursalDTO despachoDTO);
    List<DespachoSucursalDetalladoDTO> listarDespachos();
    DespachoSucursal obtenerPorId(int id);
    List<DespachoSucursal> listarPorEstado(int estadoOperacion);
    List<DespachoSucursal> buscarPorFechas(LocalDate desde, LocalDate hasta);
    void actualizarEstado(int ordenId, int nuevoEstado);
    byte[] exportarDespacho(int despachoId);
}
