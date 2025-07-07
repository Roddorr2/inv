package com.tailoy.inv.template;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.repository.SubcategoriaRepository;

public abstract class ProductoOperacionTemplate {
    @Autowired
    protected ProductoRepository repo;
    @Autowired
    protected SubcategoriaRepository subRepo;

    public final Producto ejecutar(ProductoDTO dto, Integer id) {
        validar(dto, id);
        Producto producto = construir(dto, id);
        return repo.save(producto);
    }

    protected abstract void validar(ProductoDTO dto, Integer id);

    protected abstract Producto construir(ProductoDTO dto, Integer id);

}
