package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Subcategoria;
import com.tailoy.inv.template.ProductoOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class RegistrarProductoCommand extends ProductoOperacionTemplate implements ProductoCommand {

    @Override
    protected void validar(ProductoDTO dto, Integer id) {
        if (repo.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un producto con ese código");
        }
    }

    @Override
    protected Producto construir(ProductoDTO dto, Integer id) {
        Subcategoria sub = subRepo.findById(dto.getSubcategoria().getId())
                .orElseThrow(() -> new EntityNotFoundException("Subcategoría no encontrada"));

        Producto producto = new Producto();
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setMarca(dto.getMarca());
        producto.setDescripcion(dto.getDescripcion());
        producto.setStock(dto.getStock());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setUnidadMedida(dto.getUnidadMedida());
        producto.setSubcategoria(sub);
        producto.setEstado(true);

        return producto;
    }

}
