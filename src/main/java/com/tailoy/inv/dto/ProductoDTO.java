package com.tailoy.inv.dto;

import com.tailoy.inv.model.Producto;

public class ProductoDTO {
    private int id;
    private int codigo;
    private String nombre;
    private String marca;
    private SubcategoriaDTO subcategoria;
    private int stock;
    private double precioUnitario;
    private String unidadMedida;
    private boolean estado;
    
    public ProductoDTO() {
        this.estado = true;
    }

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.codigo = producto.getCodigo();
        this.nombre = producto.getNombre();
        this.marca = producto.getMarca();
        this.subcategoria = new SubcategoriaDTO(producto.getSubcategoria());
        this.stock = producto.getStock();
        this.precioUnitario = producto.getPrecioUnitario();
        this.unidadMedida = producto.getUnidadMedida();
        this.estado = producto.isEstado();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public SubcategoriaDTO getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(SubcategoriaDTO subcategoria) {
        this.subcategoria = subcategoria;
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
