package com.tailoy.inv.service;

import java.util.List;
import java.util.Optional;

import com.tailoy.inv.model.Categoria;

public interface CategoriaService {
    Categoria registrarCategoria(Categoria categoria);
    List<Categoria> listarCategorias();
    Optional<Categoria> obtenerCategoriaPorId(int id);
    Categoria actualizarCategoria(Categoria categoria);
    void eliminarCategoria(int id);
    boolean existeCategoriaPorNombre(String nombre);
}

