package com.tailoy.inv.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HistorialAccion {
    private int id;
    private LocalDate fecha;
    private String tipoAccion;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;

    public HistorialAccion() {
        this.fecha = LocalDate.now();
    }

    public HistorialAccion(int id, LocalDate fecha, String tipoAccion, String descripcion, Usuario usuario) {
        this.id = id;
        this.fecha = fecha;
        this.tipoAccion = tipoAccion;
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
