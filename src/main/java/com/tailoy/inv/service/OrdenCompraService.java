package com.tailoy.inv.service;

import java.time.LocalDate;
import java.util.List;

import com.tailoy.inv.dto.OrdenCompraDTO;
import com.tailoy.inv.dto.OrdenCompraDetalladoDTO;
import com.tailoy.inv.model.OrdenCompra;

public interface OrdenCompraService {
    OrdenCompra registrarOrdenCompra(OrdenCompraDTO ordenDTO);
    OrdenCompra actualizarOrdenCompra(OrdenCompraDTO ordenDTO);
    OrdenCompra obtenerPorId(int id);
    List<OrdenCompraDetalladoDTO> listarOrdenes();
    List<OrdenCompra> listarPorEstado(int estadoOperacion);
    List<OrdenCompra> buscarPorFechas(LocalDate desde, LocalDate hasta);
    void actualizarEstado(int ordenId, int nuevoEstado);
    byte[] exportarOrden(int ordenId);
}
