package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.SubcategoriaDTO;
import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.model.Subcategoria;
import com.tailoy.inv.template.SubcategoriaOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class RegistrarSubcategoriaCommand extends SubcategoriaOperacionTemplate implements SubcategoriaCommand {

    @Override
    protected void validar(SubcategoriaDTO dto, Integer id) {
        if (subcategoriaRepo.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe una subcategoría con ese nombre.");
        }

        if (!categoriaRepo.existsById(dto.getCategoria().getId())) {
            throw new EntityNotFoundException("Categoría no encontrada con ID: " + dto.getCategoria().getId());
        }
    }

    @Override
    protected Subcategoria construir(SubcategoriaDTO dto, Integer id) {
        Categoria categoria = categoriaRepo.findById(dto.getCategoria().getId()).orElseThrow();

        Subcategoria nueva = new Subcategoria();
        nueva.setNombre(dto.getNombre());
        nueva.setCategoria(categoria);
        return nueva;
    }
}
