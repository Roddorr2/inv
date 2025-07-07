package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.SucursalDTO;
import com.tailoy.inv.model.Sucursal;
import com.tailoy.inv.template.SucursalOperacionTemplate;

@Component
public class RegistrarSucursalCommand extends SucursalOperacionTemplate implements SucursalCommand {

    @Override
    protected void validar(SucursalDTO dto, Integer id) {
        if (repo.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("Ya existe una sucursal con ese correo.");
        }
    }

    @Override
    protected Sucursal construir(SucursalDTO dto, Integer id) {
        Sucursal sucursal = new Sucursal();
        sucursal.setCiudad(dto.getCiudad());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setCorreo(dto.getCorreo());
        return sucursal;
    }
}
