package com.tailoy.inv.dto;

import com.tailoy.inv.model.DespachoSucursalProducto;

public class ProductoDespachoDTO {
    private int id;
    private ProductoDTO producto;
    private DespachoSucursalDTO despacho;
    private int cantidad;
    private double precioUnitario;
    private String observaciones;
    
    public ProductoDespachoDTO() {
    }

    public ProductoDespachoDTO(DespachoSucursalProducto despachoProducto) {
        this.id = despachoProducto.getId();
        this.producto = new ProductoDTO(despachoProducto.getProducto());
        this.despacho = new DespachoSucursalDTO(despachoProducto.getDespachoSucursal());
        this.cantidad = despachoProducto.getCantidad();
        this.precioUnitario = despachoProducto.getPrecioUnitario();
        this.observaciones = despachoProducto.getObservaciones();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public DespachoSucursalDTO getDespacho() {
        return despacho;
    }

    public void setDespacho(DespachoSucursalDTO despacho) {
        this.despacho = despacho;
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

    
}
