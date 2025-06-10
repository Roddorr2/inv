package com.tailoy.inv.dto;

import com.tailoy.inv.model.Subcategoria;

public class SubcategoriaDTO implements AuditDescripcion {
    private int id;
    private String nombre;
    private CategoriaDTO categoria;

    public SubcategoriaDTO() {
    }

    public SubcategoriaDTO(Subcategoria subcategoria) {
        this.id = subcategoria.getId();
        this.nombre = subcategoria.getNombre();
        this.categoria = new CategoriaDTO(subcategoria.getCategoria());
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

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getDescripcionParaHistorial() {
        return "Subcategoria - Nombre : " + nombre + ", Categoria: " + categoria.getDescripcionParaHistorial();
    }
}
