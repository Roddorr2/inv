package com.tailoy.inv.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "proveedorId", nullable = false)
    private Proveedor proveedor;
    private LocalDate fecha;
    private int estadoOperacion;
    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrdenCompraProducto> productos;
    
    public OrdenCompra() {
    }

    public OrdenCompra(int id, Proveedor proveedor, LocalDate fecha, int estadoOperacion) {
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = fecha;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(int estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public List<OrdenCompraProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<OrdenCompraProducto> productos) {
        this.productos = productos;
    }

}
