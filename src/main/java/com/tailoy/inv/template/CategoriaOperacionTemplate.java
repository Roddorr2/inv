package com.tailoy.inv.template;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.dto.CategoriaDTO;
import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.repository.CategoriaRepository;

public abstract class CategoriaOperacionTemplate {

    @Autowired
    protected CategoriaRepository repo;

    public CategoriaDTO ejecutar(CategoriaDTO dto, Integer id) {
        validar(dto, id);
        Categoria categoria = construir(dto, id);
        Categoria guardada = repo.save(categoria);
        return new CategoriaDTO(guardada);
    }

    protected abstract void validar(CategoriaDTO dto, Integer id);

    protected abstract Categoria construir(CategoriaDTO dto, Integer id);
}
