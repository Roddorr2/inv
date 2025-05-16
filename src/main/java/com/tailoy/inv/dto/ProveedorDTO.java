package com.tailoy.inv.dto;

import com.tailoy.inv.model.Proveedor;

public class ProveedorDTO {
    private int id;
    private String nombre;
    private String ruc;
    private String telefono;
    private String direccion;
    private boolean estado;

    public ProveedorDTO() {
    }

    public ProveedorDTO(Proveedor proveedor) {
        this.id = proveedor.getId();
        this.nombre = proveedor.getNombre();
        this.ruc = proveedor.getRuc();
        this.telefono = proveedor.getTelefono();
        this.direccion = proveedor.getDireccion();
        this.estado = proveedor.isEstado();
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    

}
