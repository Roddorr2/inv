package com.tailoy.inv.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tailoy.inv.dto.SubcategoriaDTO;
import com.tailoy.inv.service.impl.SubcategoriaServiceImpl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/subcategorias")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SubcategoriaController {
    private final SubcategoriaServiceImpl subcategoriaService;
    
    public SubcategoriaController(SubcategoriaServiceImpl subcategoriaService) {
        this.subcategoriaService = subcategoriaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubcategoriaDTO> registrarSubcategoria(@Validated @RequestBody SubcategoriaDTO  subcategoriaDTO) {
        SubcategoriaDTO nuevaSubcategoria = subcategoriaService.registrarSubcategoria(subcategoriaDTO);
        return ResponseEntity.ok(nuevaSubcategoria);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SubcategoriaDTO>> listarSubcategorias() {
        return ResponseEntity.ok(subcategoriaService.listarSubcategorias());
    }
    
    @GetMapping("/existe")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> existePorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(subcategoriaService.existeSubcategoriaPorNombre(nombre));
    }

}
