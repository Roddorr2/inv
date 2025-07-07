package com.tailoy.inv.command;

import org.springframework.stereotype.Component;
import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.template.ProductoOperacionTemplate;
import jakarta.persistence.EntityNotFoundException;

@Component
public class CambiarEstadoProductoCommand extends ProductoOperacionTemplate implements ProductoCommand {

    @Override
    protected void validar(ProductoDTO dto, Integer id) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        boolean nuevoEstado = dto.isEstado();
        if (producto.isEstado() == nuevoEstado) {
            throw new IllegalArgumentException("El producto ya tiene ese estado.");
        }
    }

    @Override
    protected Producto construir(ProductoDTO dto, Integer id) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        producto.setEstado(dto.isEstado());
        return producto;
    }
}
