package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.ProveedorDTO;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.template.ProveedorOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ModificarProveedorCommand extends ProveedorOperacionTemplate implements ProveedorCommand {

    @Override
    protected void validar(ProveedorDTO dto, Integer id) {
        Proveedor actual = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
        if (!actual.getRuc().equals(dto.getRuc()) && repo.existsByRuc(dto.getRuc())) {
            throw new IllegalArgumentException("Ya existe otro proveedor con ese RUC.");
        }
    }

    @Override
    protected Proveedor construir(ProveedorDTO dto, Integer id) {
        Proveedor proveedor = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));

        proveedor.setNombre(dto.getNombre());
        proveedor.setRuc(dto.getRuc());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setDireccion(dto.getDireccion());
        return proveedor;
    }
}
