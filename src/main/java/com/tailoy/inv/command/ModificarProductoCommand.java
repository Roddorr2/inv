package com.tailoy.inv.command;

import org.springframework.stereotype.Component;
import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Subcategoria;
import com.tailoy.inv.template.ProductoOperacionTemplate;
import jakarta.persistence.EntityNotFoundException;

@Component
public class ModificarProductoCommand extends ProductoOperacionTemplate implements ProductoCommand {

    @Override
    protected void validar(ProductoDTO dto, Integer id) {
        Producto productoExistente = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        if (productoExistente.getCodigo() != dto.getCodigo() && repo.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe otro producto con ese código");
        }
    }

    @Override
    protected Producto construir(ProductoDTO dto, Integer id) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setMarca(dto.getMarca());
        producto.setDescripcion(dto.getDescripcion());
        producto.setStock(dto.getStock());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setUnidadMedida(dto.getUnidadMedida());

        if (dto.getSubcategoria().getId() != producto.getSubcategoria().getId()) {
            Subcategoria nuevaSub = subRepo.findById(dto.getSubcategoria().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Subcategoría no encontrada"));
            producto.setSubcategoria(nuevaSub);
        }

        return producto;
    }
}
