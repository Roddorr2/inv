package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.ProveedorDTO;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.template.ProveedorOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CambiarEstadoProveedorCommand extends ProveedorOperacionTemplate implements ProveedorCommand {

    @Override
    protected void validar(ProveedorDTO dto, Integer id) {
        repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
    }

    @Override
    protected Proveedor construir(ProveedorDTO dto, Integer id) {
        Proveedor proveedor = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
        proveedor.setEstado(dto.isEstado());
        return proveedor;
    }
}
