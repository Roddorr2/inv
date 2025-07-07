package com.tailoy.inv.dto;

public class MovimientoAlmacenDetalleDTO {
    private int id;
    private String producto;
    private int codigo;
    private int cantidad;
    private String tipoAlmacen;

    public MovimientoAlmacenDetalleDTO() {
    }

    public MovimientoAlmacenDetalleDTO(int id, String producto, int codigo, int cantidad, String tipoAlmacen) {
        this.id = id;
        this.producto = producto;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.tipoAlmacen = tipoAlmacen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoAlmacen() {
        return tipoAlmacen;
    }

    public void setTipoAlmacen(String tipoAlmacen) {
        this.tipoAlmacen = tipoAlmacen;
    }

}
