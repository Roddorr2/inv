package com.tailoy.inv.template;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.dto.ProveedorDTO;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.repository.ProveedorRepository;

public abstract class ProveedorOperacionTemplate {

    @Autowired
    protected ProveedorRepository repo;

    public final Proveedor ejecutar(ProveedorDTO dto, Integer id) {
        validar(dto, id);
        Proveedor proveedor = construir(dto, id);
        return repo.save(proveedor);
    }

    protected abstract void validar(ProveedorDTO dto, Integer id);

    protected abstract Proveedor construir(ProveedorDTO dto, Integer id);
}
