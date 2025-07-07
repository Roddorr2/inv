package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.SubcategoriaDTO;
import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.model.Subcategoria;
import com.tailoy.inv.template.SubcategoriaOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ActualizarSubcategoriaCommand extends SubcategoriaOperacionTemplate implements SubcategoriaCommand {

    @Override
    protected void validar(SubcategoriaDTO dto, Integer id) {
        if (!subcategoriaRepo.existsById(id)) {
            throw new EntityNotFoundException("Subcategoría no encontrada con ID: " + id);
        }

        Subcategoria existente = subcategoriaRepo.findById(id).orElseThrow();
        if (!existente.getNombre().equalsIgnoreCase(dto.getNombre())
                && subcategoriaRepo.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe otra subcategoría con ese nombre.");
        }

        if (!categoriaRepo.existsById(dto.getCategoria().getId())) {
            throw new EntityNotFoundException("Categoría no encontrada con ID: " + dto.getCategoria().getId());
        }
    }

    @Override
    protected Subcategoria construir(SubcategoriaDTO dto, Integer id) {
        Subcategoria existente = subcategoriaRepo.findById(id).orElseThrow();
        Categoria categoria = categoriaRepo.findById(dto.getCategoria().getId()).orElseThrow();

        existente.setNombre(dto.getNombre());
        existente.setCategoria(categoria);
        return existente;
    }
}
