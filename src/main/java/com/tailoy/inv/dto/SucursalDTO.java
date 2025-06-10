package com.tailoy.inv.dto;

import com.tailoy.inv.model.Sucursal;

public class SucursalDTO implements AuditDescripcion {
    private int id;
    private String ciudad;
    private String direccion;
    private String correo;

    public SucursalDTO() {
    }

    public SucursalDTO(Sucursal sucursal) {
        this.id = sucursal.getId();
        this.ciudad = sucursal.getCiudad();
        this.direccion = sucursal.getDireccion();
        this.correo = sucursal.getCorreo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }    

    @Override
    public String getDescripcionParaHistorial() {
        return "Sucursal - Ciudad : " + ciudad + ", Dirección: " + direccion + ", Correo: " + correo;
    }

}
