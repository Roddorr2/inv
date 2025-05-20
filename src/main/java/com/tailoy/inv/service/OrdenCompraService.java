package com.tailoy.inv.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tailoy.inv.dto.OrdenCompraDTO;
import com.tailoy.inv.model.OrdenCompra;

public interface OrdenCompraService {
    OrdenCompra registrarOrdenCompra(OrdenCompraDTO ordenDTO);
    OrdenCompra obtenerPorId(int id);
    List<OrdenCompra> listarOrdenes();
    List<OrdenCompra> listarPorEstado(int estadoOperacion);
    List<OrdenCompra> buscarPorFechas(LocalDateTime desde, LocalDateTime hasta);
    void actualizarEstado(int ordenId, int nuevoEstado);
    byte[] exportarOrden(int ordenId);
}
