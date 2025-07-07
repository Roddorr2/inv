package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.CategoriaDTO;
import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.template.CategoriaOperacionTemplate;

@Component
public class RegistrarCategoriaCommand extends CategoriaOperacionTemplate implements CategoriaCommand {

    @Override
    protected void validar(CategoriaDTO dto, Integer id) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio.");
        }

        if (repo.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
        }
    }

    @Override
    protected Categoria construir(CategoriaDTO dto, Integer id) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre().toUpperCase());
        return categoria;
    }
}
