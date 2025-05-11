package com.tailoy.inv.dto;

import java.time.LocalDate;
import java.util.List;

public class DespachoSucursalDTO {
    private int sucursalId;
    private LocalDate fechaDespacho;
    private List<ProductoDespachoDTO> productos;
    private int usuarioId;
    
    public DespachoSucursalDTO() {
    }

    public DespachoSucursalDTO(int sucursalId, LocalDate fechaDespacho, List<ProductoDespachoDTO> productos,
            int usuarioId) {
        this.sucursalId = sucursalId;
        this.fechaDespacho = fechaDespacho;
        this.productos = productos;
        this.usuarioId = usuarioId;
    }

    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    public LocalDate getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(LocalDate fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public List<ProductoDespachoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDespachoDTO> productos) {
        this.productos = productos;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    
}
