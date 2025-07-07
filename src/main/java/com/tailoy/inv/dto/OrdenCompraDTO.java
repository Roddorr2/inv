package com.tailoy.inv.dto;

import java.time.LocalDate;
import java.util.List;

import com.tailoy.inv.model.OrdenCompra;

public class OrdenCompraDTO {
    private int id;
    private ProveedorDTO proveedor;
    private LocalDate fecha;
    private List<ProductoOrdenDTO> productos;
    private int estadoOperacion;

    public OrdenCompraDTO() {
    }

    public OrdenCompraDTO(OrdenCompra orden) {
        this.id = orden.getId();
        this.proveedor = new ProveedorDTO(orden.getProveedor());
        this.fecha = orden.getFecha();
        this.estadoOperacion = orden.getEstadoOperacion();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<ProductoOrdenDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoOrdenDTO> productos) {
        this.productos = productos;
    }

    public int getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(int estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }
    
}
