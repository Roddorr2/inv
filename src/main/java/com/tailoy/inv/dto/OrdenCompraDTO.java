package com.tailoy.inv.dto;

import java.time.LocalDate;
import java.util.List;

public class OrdenCompraDTO {
    private int proveedorId;
    private LocalDate fechaEntregaEstimada;
    private List<ItemOrdenCompraDTO> items;
    private int usuarioId;

    public OrdenCompraDTO(int proveedorId, LocalDate fechaEntregaEstimada, List<ItemOrdenCompraDTO> items,
            int usuarioId) {
        this.proveedorId = proveedorId;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.items = items;
        this.usuarioId = usuarioId;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public List<ItemOrdenCompraDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemOrdenCompraDTO> items) {
        this.items = items;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }    
}
