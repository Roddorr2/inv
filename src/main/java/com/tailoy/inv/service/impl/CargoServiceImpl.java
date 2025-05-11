package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.repository.CargoRepository;
import com.tailoy.inv.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService {
    @Autowired
    private CargoRepository cargoRepository;
    
    @Override
    public Cargo registrarCargo(Cargo cargo) {
        if (cargo == null || cargo.getNombre() == null || cargo.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cargo es obligatorio.");
        }

        if (existeCargoPorNombre(cargo.getNombre())) {
            throw new IllegalArgumentException("Ya existe un cargo con ese nombre");
        }

        return cargoRepository.save(cargo);
    }

    @Override
    public List<Cargo> listarCargos() {
        return cargoRepository.findAll();
    }

    @Override
    public Optional<Cargo> obtenerCargosPorId(int id) {
        return cargoRepository.findById(id);
    }

    @Override
    public Cargo actualizarCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @Override
    public boolean existeCargoPorNombre(String nombre) {
        return cargoRepository.existsByNombre(nombre);
    }
}
