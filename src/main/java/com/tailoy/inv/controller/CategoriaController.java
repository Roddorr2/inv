package com.tailoy.inv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tailoy.inv.dto.CategoriaDTO;
import com.tailoy.inv.dto.SubcategoriaDTO;
import com.tailoy.inv.service.impl.CategoriaServiceImpl;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoriaController {
    @Autowired
    private CategoriaServiceImpl categoriaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaDTO> registrarCategoria(@Validated @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO nueva = categoriaService.registrarCategoria(categoriaDTO);
        return ResponseEntity.ok(nueva);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable int id) {
        CategoriaDTO categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizarCategoria(@PathVariable int id, @Validated @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO actualizada  = categoriaService.actualizarCategoria(id, categoriaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/existe")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> existePorNombre(@RequestParam String nombre) {
        boolean existe = categoriaService.existeCategoriaPorNombre(nombre);;
        return ResponseEntity.ok(existe);
    }
    
    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE_DE_COMPRAS', 'ALMACENERO')")
    public ResponseEntity<List<CategoriaDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(categoriaService.buscarPorNombre(nombre));
    }

}
