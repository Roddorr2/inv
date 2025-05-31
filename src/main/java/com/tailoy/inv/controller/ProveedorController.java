package com.tailoy.inv.controller;

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
@RequestMapping("/admin/proveedores")
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

    @GetMapping("/{id}")
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
}
