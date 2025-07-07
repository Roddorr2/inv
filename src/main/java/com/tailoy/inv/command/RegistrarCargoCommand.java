package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.CargoDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.template.CargoOperacionTemplate;

@Component
public class RegistrarCargoCommand extends CargoOperacionTemplate implements CargoCommand {

    @Override
    protected void validar(CargoDTO dto, Integer id) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cargo es obligatorio.");
        }
        if (cargoRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe un cargo con ese nombre");
        }
    }

    @Override
    protected Cargo construir(CargoDTO dto, Integer id) {
        Cargo cargo = new Cargo();
        cargo.setNombre(dto.getNombre().toUpperCase());
        return cargo;
    }
}
