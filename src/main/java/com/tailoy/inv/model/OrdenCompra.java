package com.tailoy.inv.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "proveedorId", nullable = false)
    private Proveedor proveedor;
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "productoId", nullable = false)
    private Usuario usuario;
    private int estadoOperacion;
    
    public OrdenCompra() {
    }

    public OrdenCompra(int id, Proveedor proveedor, Date fecha, Usuario usuario, int estadoOperacion) {
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.usuario = usuario;
        this.estadoOperacion = estadoOperacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(int estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }
}
