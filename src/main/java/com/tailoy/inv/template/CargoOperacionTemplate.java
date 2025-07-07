package com.tailoy.inv.template;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.dto.CargoDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.repository.CargoRepository;

public abstract class CargoOperacionTemplate {

    @Autowired
    protected CargoRepository cargoRepository;

    public CargoDTO ejecutar(CargoDTO dto, Integer id) {
        validar(dto, id);
        Cargo cargo = construir(dto, id);
        Cargo guardado = cargoRepository.save(cargo);
        return new CargoDTO(guardado);
    }

    protected abstract void validar(CargoDTO dto, Integer id);

    protected abstract Cargo construir(CargoDTO dto, Integer id);
}
