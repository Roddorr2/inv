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

import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.service.impl.ProductoServiceImpl;

@RestController
@RequestMapping("api/productos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductoController {
    @Autowired
    private ProductoServiceImpl productoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE_DE_COMPRAS')")
    public ResponseEntity<Producto> registrar(@Validated @RequestBody ProductoDTO productoDTO) {
        Producto nuevo = productoService.registrarProducto(productoDTO);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> modificarProducto(@PathVariable int id, @Validated @RequestBody ProductoDTO productoDTO) {
        Producto actualizado = productoService.modificarProducto(id, productoDTO);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/estado") 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cambiarEstado(@PathVariable int id, @RequestParam boolean estado) {
        productoService.cambiarEstadoProducto(id, estado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable int id) {
        Producto producto = productoService.obtenerPorCodigo(id);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/codigo/{codigo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> obtenerPorCodigo(@PathVariable int codigo) {
        Producto producto = productoService.obtenerPorCodigo(codigo);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/marca/{marca}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> obtenerPorMarca(@PathVariable String marca) {
        Producto producto = productoService.obtenerPorMarca(marca);
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/activos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Producto>> listarProductosActivos() {
        List<Producto> activos = productoService.listarActivos();
        return ResponseEntity.ok(activos);
    }

    @GetMapping("/nombre/{nombre}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Producto>> buscarPorNombre(@PathVariable String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/existe")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> existePorCodigo(@RequestParam int codigo) {
        boolean existe = productoService.existeCodigoProducto(codigo);
        return ResponseEntity.ok(existe);
    }

}
