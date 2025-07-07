package com.tailoy.inv.dto;

import java.time.LocalDate;

public class DespachoSucursalDetalladoDTO {
    private int id;
    private String correoSucursal;
    private LocalDate fecha;
    private String estadoOperacion;
    private String producto;
    private double precioUnitario;
    private int cantidad;
    private double total;
    private String observaciones;

    public DespachoSucursalDetalladoDTO() {
    }

    public DespachoSucursalDetalladoDTO(int id, String correoSucursal, LocalDate fecha, String estadoOperacion, String producto, double precioUnitario, int cantidad,
            double total, String observaciones) {
        this.id = id;
        this.correoSucursal = correoSucursal;
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
    public String getCorreoSucursal() {
        return correoSucursal;
    }

    public void setCorreoSucursal(String correoSucursal) {
        this.correoSucursal = correoSucursal;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
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

    public String getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(String estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

}
