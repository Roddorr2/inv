package com.tailoy.inv.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DespachoSucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int estadoOperacion;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "sucursalId", nullable = false)
    private Sucursal sucursal;
    @OneToMany(mappedBy="despachoSucursal", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<DespachoSucursalProducto> productos;
    
    public DespachoSucursal() {
    }

    public DespachoSucursal(int id, int estadoOperacion, LocalDate fecha, Sucursal sucursal) {
        this.id = id;
        this.estadoOperacion = estadoOperacion;
        this.fecha = fecha;
        this.sucursal = sucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(int estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }    
}
