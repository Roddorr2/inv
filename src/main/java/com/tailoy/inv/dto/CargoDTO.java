package com.tailoy.inv.dto;

import com.tailoy.inv.model.Cargo;

public class CargoDTO {
    private int id;
    private String nombre;

    public CargoDTO() {
    }

    public CargoDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nombre = cargo.getNombre();
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
}
