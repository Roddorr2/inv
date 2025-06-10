package com.tailoy.inv.dto;

import java.time.LocalDateTime;

import com.tailoy.inv.model.HistorialAccion;

public class HistorialDTO {
    private int id;
    private LocalDateTime fecha;
    private int tipoAccion;
    private String descripcion;
    private int modulo;
    private UsuarioDTO usuario;

    public HistorialDTO(HistorialAccion historial) {
        this.id = historial.getId();
        this.fecha = historial.getFecha();
        this.tipoAccion = historial.getTipoAccion();
        this.descripcion = historial.getDescripcion();
        this.modulo = historial.getModulo();
        this.usuario = new UsuarioDTO(historial.getUsuario());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(int tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
}
