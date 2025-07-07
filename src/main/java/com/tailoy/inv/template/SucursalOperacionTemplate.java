package com.tailoy.inv.template;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.dto.SucursalDTO;
import com.tailoy.inv.model.Sucursal;
import com.tailoy.inv.repository.SucursalRepository;

public abstract class SucursalOperacionTemplate {

    @Autowired
    protected SucursalRepository repo;

    public SucursalDTO ejecutar(SucursalDTO dto, Integer id) {
        validar(dto, id);
        Sucursal sucursal = construir(dto, id);
        Sucursal guardada = repo.save(sucursal);
        return new SucursalDTO(guardada);
    }

    protected abstract void validar(SucursalDTO dto, Integer id);

    protected abstract Sucursal construir(SucursalDTO dto, Integer id);
}
