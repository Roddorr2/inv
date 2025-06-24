package com.tailoy.inv.service;

import java.util.List;
import java.util.Optional;

import com.tailoy.inv.dto.CargoDTO;
import com.tailoy.inv.model.Cargo;

public interface CargoService {
    CargoDTO registrarCargo(CargoDTO cargoDTO);
    List<Cargo> listarCargos();
    Optional<Cargo> obtenerCargosPorId(int id);
    CargoDTO actualizarCargo(int id, CargoDTO cargoDTO);
    boolean existeCargoPorNombre(String nombre);
    List<CargoDTO> buscarCargoPorNombre(String nombre);
}
