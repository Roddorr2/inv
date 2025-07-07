package com.tailoy.inv.template;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.dto.SubcategoriaDTO;
import com.tailoy.inv.model.Subcategoria;
import com.tailoy.inv.repository.CategoriaRepository;
import com.tailoy.inv.repository.SubcategoriaRepository;

public abstract class SubcategoriaOperacionTemplate {

    @Autowired
    protected SubcategoriaRepository subcategoriaRepo;

    @Autowired
    protected CategoriaRepository categoriaRepo;

    public SubcategoriaDTO ejecutar(SubcategoriaDTO dto, Integer id) {
        validar(dto, id);
        Subcategoria subcategoria = construir(dto, id);
        Subcategoria guardada = subcategoriaRepo.save(subcategoria);
        return new SubcategoriaDTO(guardada);
    }

    protected abstract void validar(SubcategoriaDTO dto, Integer id);

    protected abstract Subcategoria construir(SubcategoriaDTO dto, Integer id);
}
