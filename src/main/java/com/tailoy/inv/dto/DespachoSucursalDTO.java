package com.tailoy.inv.dto;


import java.time.LocalDateTime;
import java.util.List;

import com.tailoy.inv.model.DespachoSucursal;


public class DespachoSucursalDTO {
    private int id;
    private int estadoOperacion;
    private LocalDateTime fechaDespacho;
    private SucursalDTO sucursal;
    private List<ProductoDespachoDTO> productos;
    
    
    public DespachoSucursalDTO() {
        this.estadoOperacion = 1;
    }

    public DespachoSucursalDTO(DespachoSucursal despacho) {
        this.id = despacho.getId();
        this.estadoOperacion = despacho.getEstadoOperacion();
        this.fechaDespacho = despacho.getFecha();
        this.sucursal = new SucursalDTO(despacho.getSucursal());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public int getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(int estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public LocalDateTime getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(LocalDateTime fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public List<ProductoDespachoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDespachoDTO> productos) {
        this.productos = productos;
    }
    
}
