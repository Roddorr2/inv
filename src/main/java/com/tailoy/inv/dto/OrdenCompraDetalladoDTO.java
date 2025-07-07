package com.tailoy.inv.dto;

import java.time.LocalDate;

public class OrdenCompraDetalladoDTO {
    private int id;
    private String proveedor;
    private LocalDate fecha;
    private String estadoOperacion;
    private String producto;
    private double precioUnitario;
    private Integer cantidad;
    private double total;
    private String observaciones;
    
    public OrdenCompraDetalladoDTO() {
    }

    public OrdenCompraDetalladoDTO(int id, String proveedor, LocalDate fecha, String estadoOperacion, String producto,
            double precioUnitario, Integer cantidad, double total, String observaciones) {
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.estadoOperacion = estadoOperacion;
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.total = total;
        this.observaciones = observaciones;
    }



    public OrdenCompraDetalladoDTO(String proveedor, LocalDate fecha, String estadoOperacion, String producto,
            double precioUnitario, Integer cantidad, double total, String observaciones) {
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.estadoOperacion = estadoOperacion;
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.total = total;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(String estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
