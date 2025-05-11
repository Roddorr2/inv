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
    private int tipoAlmacen;

    public MovimientoAlmacen() {
    }

    public MovimientoAlmacen(int id, int tipoAlmacen) {
        this.id = id;
        this.tipoAlmacen = tipoAlmacen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoAlmacen() {
        return tipoAlmacen;
    }

    public void setTipoAlmacen(int tipoAlmacen) {
        this.tipoAlmacen = tipoAlmacen;
    }
}
