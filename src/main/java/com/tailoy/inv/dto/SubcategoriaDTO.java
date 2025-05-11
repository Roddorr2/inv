package com.tailoy.inv.dto;

public class SubcategoriaDTO {
    private int id;
    private String nombre;
    private int categoriaId;

    public SubcategoriaDTO() {
    }

    public SubcategoriaDTO(int id, String nombre, int categoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.categoriaId = categoriaId;
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

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
}
