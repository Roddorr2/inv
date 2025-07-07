package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.CategoriaDTO;
import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.template.CategoriaOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ActualizarCategoriaCommand extends CategoriaOperacionTemplate implements CategoriaCommand {

    @Override
    protected void validar(CategoriaDTO dto, Integer id) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio.");
        }

        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada con ID: " + id);
        }

        Categoria existente = repo.findById(id).orElseThrow();
        if (!existente.getNombre().equalsIgnoreCase(dto.getNombre())
                && repo.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe otra categoría con ese nombre.");
        }
    }

    @Override
    protected Categoria construir(CategoriaDTO dto, Integer id) {
        Categoria categoria = repo.findById(id).orElseThrow();
        categoria.setNombre(dto.getNombre().toUpperCase());
        return categoria;
    }
}
