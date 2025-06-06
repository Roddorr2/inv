package com.tailoy.inv.service;

import java.util.List;
import java.util.Optional;

import com.tailoy.inv.model.Cargo;

public interface CargoService {
    Cargo registrarCargo(Cargo cargo);
    List<Cargo> listarCargos();
    Optional<Cargo> obtenerCargosPorId(int id);
    Cargo actualizarCargo(Cargo cargo);
    boolean existeCargoPorNombre(String nombre);
    List<Cargo> buscarCargoPorNombre(String nombre);
}
