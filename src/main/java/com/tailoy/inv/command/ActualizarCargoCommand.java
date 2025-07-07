package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.CargoDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.template.CargoOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ActualizarCargoCommand extends CargoOperacionTemplate implements CargoCommand {

    @Override
    protected void validar(CargoDTO dto, Integer id) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cargo es obligatorio.");
        }

        Cargo existente = cargoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cargo no existe."));

        if (!existente.getNombre().equalsIgnoreCase(dto.getNombre())
                && cargoRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe otro cargo con ese nombre.");
        }
    }

    @Override
    protected Cargo construir(CargoDTO dto, Integer id) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cargo no existe."));
        cargo.setNombre(dto.getNombre().toUpperCase());
        return cargo;
    }
}
