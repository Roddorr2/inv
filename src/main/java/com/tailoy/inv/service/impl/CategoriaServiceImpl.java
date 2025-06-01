package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.dto.CategoriaDTO;
import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.repository.CategoriaRepository;
import com.tailoy.inv.service.CategoriaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDTO registrarCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaDTO == null || categoriaDTO.getNombre() == null || categoriaDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio.");
        }

        if (existeCategoriaPorNombre(categoriaDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());

        Categoria guardada = categoriaRepository.save(categoria);
        return new CategoriaDTO(guardada);
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorId(int id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("La categoría no existe"));
            return new CategoriaDTO(categoria);
    }

    @Override
    public CategoriaDTO actualizarCategoria(int id, CategoriaDTO categoriaDTO) {
        if (categoriaDTO == null || categoriaDTO.getNombre() == null || categoriaDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de la categoría es obligatorio.");
        }
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("La categoría no existe."));        

        categoria.setNombre(categoriaDTO.getNombre());

        Categoria actualizada = categoriaRepository.save(categoria);
        return new CategoriaDTO(actualizada);
    }

    @Override
    public boolean existeCategoriaPorNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre.trim());
    }
}
