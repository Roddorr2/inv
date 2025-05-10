package com.tailoy.inv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MovimientoAlmacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipoAlmacen;

    public MovimientoAlmacen() {
    }

    public MovimientoAlmacen(int id, String tipoAlmacen) {
        this.id = id;
        this.tipoAlmacen = tipoAlmacen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoAlmacen() {
        return tipoAlmacen;
    }

    public void setTipoAlmacen(String tipoAlmacen) {
        this.tipoAlmacen = tipoAlmacen;
    }
}
