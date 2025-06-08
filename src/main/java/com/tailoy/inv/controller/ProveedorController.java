package com.tailoy.inv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tailoy.inv.dto.ProveedorDTO;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.service.impl.ProveedorServiceImpl;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProveedorController {

    @Autowired
    private ProveedorServiceImpl proveedorService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE_DE_COMPRAS')")
    public ResponseEntity<Proveedor> registrar(@Validated @RequestBody ProveedorDTO proveedorDTO) {
        Proveedor nuevoProveedor = proveedorService.registrarProveedor(proveedorDTO);
        return ResponseEntity.ok(nuevoProveedor);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE_COMPRAS')")
    public ResponseEntity<List<Proveedor>> listar() {
        return ResponseEntity.ok(proveedorService.listarProveedores());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE_COMPRAS')")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(proveedorService.obtenerPorId(id));
    }

    @GetMapping("/ruc/{ruc}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Proveedor> obtenerPorRuc(@PathVariable String ruc) {
        return ResponseEntity.ok(proveedorService.obtenerPorRuc(ruc));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Proveedor> actualizar(@PathVariable int id, @Validated @RequestBody ProveedorDTO proveedorDTO) {
        Proveedor proveedorActualizado = proveedorService.modificarProveedor(id, proveedorDTO);
        return ResponseEntity.ok(proveedorActualizado);
    }

    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cambiarEstado(@PathVariable int id, @RequestParam boolean estado) {
        proveedorService.cambiarEstadoProveedor(id, estado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE_COMPRAS')")
    public ResponseEntity<List<Proveedor>> buscarProveedor(@RequestParam String q) {
        List<Proveedor> proveedores = proveedorService.buscarPorNombreORucOTelefonoODireccion(q);
        return ResponseEntity.ok(proveedores);
    }
    
    @GetMapping("/existe-nombre")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> existePorNombre(@RequestParam String nombre) {
        boolean existe = proveedorService.existeProveedorPorNombre(nombre);
        return ResponseEntity.ok(existe);
    }
    
    @GetMapping("/existe-ruc")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> existePorRuc(@RequestParam String ruc) {
        boolean existe = proveedorService.existeProveedoroPorRuc(ruc);
        return ResponseEntity.ok(existe);
    }
}

