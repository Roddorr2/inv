package com.tailoy.inv.dto;

import com.tailoy.inv.model.Usuario;

public class UsuarioDTO implements AuditDescripcion {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;
    private CargoDTO cargo;
    private boolean estado;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.correo = usuario.getCorreo();
        this.contrasena = usuario.getContrasena();
        this.cargo = new CargoDTO(usuario.getCargo());
        this.estado = usuario.isEstado();
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public CargoDTO getCargo() {
        return cargo;
    }

    public void setCargo(CargoDTO cargo) {
        this.cargo = cargo;
    }

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

    @Override
    public String getDescripcionParaHistorial() {
        return "Nombre : " + nombre + ", Correo: " + correo + ", Cargo: " + cargo.getDescripcionParaHistorial();
    }
}
