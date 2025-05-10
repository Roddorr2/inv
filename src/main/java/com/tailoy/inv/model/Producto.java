package com.tailoy.inv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int stock;
    private double precioUnitario;
    private String unidadMedida;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "subcategoriaId", referencedColumnName = "id")
    private Subcategoria subcategoria;

    public Producto() {
    }

    public Producto(int id, String nombre, int stock, double precioUnitario, String unidadMedida, boolean estado, Subcategoria subcategoria) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.unidadMedida = unidadMedida;
        this.estado = estado;
        this.subcategoria = subcategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }
}
