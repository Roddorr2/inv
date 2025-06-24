package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.dto.CargoDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.repository.CargoRepository;
import com.tailoy.inv.service.CargoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CargoServiceImpl implements CargoService {
    @Autowired
    private CargoRepository cargoRepository;
    
    @Auditable(
		accion = "Registro de cargos", 
		tipo = TipoAccionEnum.REGISTRO, 
		modulo = ModuloEnum.CARGO
		)
    @Override
    public CargoDTO registrarCargo(CargoDTO cargoDTO) {
        if (cargoDTO == null || cargoDTO.getNombre() == null || cargoDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cargo es obligatorio.");
        }

        if (existeCargoPorNombre(cargoDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe un cargo con ese nombre");
        }

        Cargo cargo = new Cargo();
        cargo.setNombre(cargoDTO.getNombre().toUpperCase());

        Cargo guardado = cargoRepository.save(cargo);
        return new CargoDTO(guardado);
    }

    @Override
    public List<Cargo> listarCargos() {
        return cargoRepository.findAll();
    }

    @Override
    public Optional<Cargo> obtenerCargosPorId(int id) {
        return cargoRepository.findById(id);
    }

    @Auditable(
		accion = "Modificación de cargos", 
		tipo = TipoAccionEnum.MODIFICACION, 
		modulo = ModuloEnum.CARGO
		)
    @Override
    public CargoDTO actualizarCargo(int id, CargoDTO cargoDTO) {
        if (cargoDTO == null || cargoDTO.getNombre() == null || cargoDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del cargo es obligatorio.");
        }
        Cargo cargo = cargoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("El cargo no existe."));        

        cargo.setNombre(cargoDTO.getNombre().toUpperCase());

        Cargo actualizado = cargoRepository.save(cargo);
        return new CargoDTO(actualizado);
    }

    @Override
    public boolean existeCargoPorNombre(String nombre) {
        return cargoRepository.existsByNombre(nombre.trim());
    }
    
    @Override
    public List<CargoDTO> buscarCargoPorNombre(String nombre) {
        List<CargoDTO> cargos = cargoRepository.findByNombreContainingIgnoreCase(nombre);
        return cargos;
    }
}
