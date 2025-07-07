package com.tailoy.inv.dto;

import com.tailoy.inv.model.OrdenCompraProducto;

public class ProductoOrdenDTO {
    private int id;
    private ProductoDTO producto;
    private int cantidad;
    private double precioUnitario;
    private String observaciones;

    public ProductoOrdenDTO() {
        
    }

    public ProductoOrdenDTO(OrdenCompraProducto compraProducto) {
        this.id = compraProducto.getId();
        this.producto = new ProductoDTO(compraProducto.getProducto());
        this.cantidad = compraProducto.getCantidad();
        this.precioUnitario = compraProducto.getPrecioUnitario();
        this.observaciones = compraProducto.getObservaciones();
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
