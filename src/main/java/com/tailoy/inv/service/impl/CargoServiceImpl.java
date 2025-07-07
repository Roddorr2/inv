package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.command.ActualizarCargoCommand;
import com.tailoy.inv.command.RegistrarCargoCommand;
import com.tailoy.inv.dto.CargoDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.repository.CargoRepository;
import com.tailoy.inv.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService {
    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private ActualizarCargoCommand actualizarCargoCommand;

    @Autowired
    private RegistrarCargoCommand registrarCargoCommand;

    @Auditable(accion = "Registro de cargos", tipo = TipoAccionEnum.REGISTRO, modulo = ModuloEnum.CARGO)
    @Override
    public CargoDTO registrarCargo(CargoDTO cargoDTO) {
        return registrarCargoCommand.ejecutar(cargoDTO, null);
    }

    @Override
    public List<Cargo> listarCargos() {
        return cargoRepository.findAll();
    }

    @Override
    public Optional<Cargo> obtenerCargosPorId(int id) {
        return cargoRepository.findById(id);
    }

    @Auditable(accion = "Modificación de cargos", tipo = TipoAccionEnum.MODIFICACION, modulo = ModuloEnum.CARGO)
    @Override
    public CargoDTO actualizarCargo(int id, CargoDTO cargoDTO) {
        return actualizarCargoCommand.ejecutar(cargoDTO, id);
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
