package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.SucursalDTO;
import com.tailoy.inv.model.Sucursal;
import com.tailoy.inv.template.SucursalOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ActualizarSucursalCommand extends SucursalOperacionTemplate implements SucursalCommand {

    @Override
    protected void validar(SucursalDTO dto, Integer id) {
        Sucursal existente = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con ID: " + id));

        if (!existente.getCorreo().equalsIgnoreCase(dto.getCorreo()) && repo.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("Ya existe otra sucursal con ese correo.");
        }
    }

    @Override
    protected Sucursal construir(SucursalDTO dto, Integer id) {
        Sucursal sucursal = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con ID: " + id));

        sucursal.setCiudad(dto.getCiudad());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setCorreo(dto.getCorreo());
        return sucursal;
    }
}
