package com.tailoy.inv.service;

import java.util.List;
import com.tailoy.inv.dto.CategoriaDTO;

public interface CategoriaService {
    CategoriaDTO registrarCategoria(CategoriaDTO categoriaDTO);
    List<CategoriaDTO> listarCategorias();
    CategoriaDTO obtenerCategoriaPorId(int id);
    CategoriaDTO actualizarCategoria(int id, CategoriaDTO categoriaDTO);
    boolean existeCategoriaPorNombre(String nombre);
}

