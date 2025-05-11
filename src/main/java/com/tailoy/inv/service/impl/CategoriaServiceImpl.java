package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.repository.CategoriaRepository;
import com.tailoy.inv.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria registrarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio.");
        }

        if (existeCategoriaPorNombre(categoria.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }

        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(int id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de la categoría es obligatorio.");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public boolean existeCategoriaPorNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre.trim());
    }
}
