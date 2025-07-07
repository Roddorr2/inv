package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.ProveedorDTO;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.template.ProveedorOperacionTemplate;

@Component
public class RegistrarProveedorCommand extends ProveedorOperacionTemplate implements ProveedorCommand {

    @Override
    protected void validar(ProveedorDTO dto, Integer id) {
        if (repo.existsByRuc(dto.getRuc())) {
            throw new IllegalArgumentException("Ya existe un proveedor con ese RUC.");
        }
    }

    @Override
    protected Proveedor construir(ProveedorDTO dto, Integer id) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(dto.getNombre());
        proveedor.setRuc(dto.getRuc());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setEstado(true);
        return proveedor;
    }
}
