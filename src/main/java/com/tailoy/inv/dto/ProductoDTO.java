package com.tailoy.inv.dto;


public class ProductoDTO {
    private String nombre;
    private int subcategoriaId;
    private int stock;
    private double precioUnitario;
    private String unidadMedida;
    private boolean estado = true;
    
    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, int subcategoriaId, int stock, double precioUnitario, String unidadMedida,
            boolean estado) {
        this.nombre = nombre;
        this.subcategoriaId = subcategoriaId;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.unidadMedida = unidadMedida;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSubcategoriaId() {
        return subcategoriaId;
    }

    public void setSubcategoriaId(int subcategoriaId) {
        this.subcategoriaId = subcategoriaId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
}
