package com.tailoy.inv.dto;

import java.time.LocalDate;

public class MovimientoInventarioDetalleDTO {
    private int id;
    private int cantidad;
    private String tipoMovimiento;
    private String producto;
    private String usuario;
    private LocalDate fecha;

    public MovimientoInventarioDetalleDTO() {
    }

    public MovimientoInventarioDetalleDTO(int id, int cantidad, String tipoMovimiento, LocalDate fecha, String producto,
            String usuario) {
        this.id = id;
        this.cantidad = cantidad;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = fecha;
        this.producto = producto;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
