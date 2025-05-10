package com.tailoy.inv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DespachoSucursalProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cantidad;
    private double precioUnitario;
    private String observaciones;
    @ManyToOne
    @JoinColumn(name = "despachoSucursalId", nullable = false)
    private DespachoSucursal despachoSucursal;
    @ManyToOne
    @JoinColumn(name = "productoId", nullable = false)
    private Producto producto;

    public DespachoSucursalProducto() {
    }

    public DespachoSucursalProducto(int id, int cantidad, double precioUnitario, String observaciones,
            DespachoSucursal despachoSucursal, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.observaciones = observaciones;
        this.despachoSucursal = despachoSucursal;
        this.producto = producto;
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public DespachoSucursal getDespachoSucursal() {
        return despachoSucursal;
    }

    public void setDespachoSucursal(DespachoSucursal despachoSucursal) {
        this.despachoSucursal = despachoSucursal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    

}
