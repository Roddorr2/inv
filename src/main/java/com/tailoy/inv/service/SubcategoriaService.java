package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.dto.SubcategoriaDTO;

public interface SubcategoriaService {
    SubcategoriaDTO registrarSubcategoria(SubcategoriaDTO subcategoriaDTO);
    List<SubcategoriaDTO> listarSubcategorias();
    boolean existeSubcategoriaPorNombre(String nombre);
}
