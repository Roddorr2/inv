package com.tailoy.inv.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tailoy.inv.dto.CargoDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.service.CargoService;

@RestController
@RequestMapping("/api/cargos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CargoDTO> registrarCargo(@Validated @RequestBody CargoDTO cargoDTO) {
        CargoDTO nuevoCargo = cargoService.registrarCargo(cargoDTO);
        return ResponseEntity.ok(nuevoCargo);
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> listarCargos() {
        return ResponseEntity.ok(cargoService.listarCargos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> obtenerCargoPorId(@PathVariable int id) {
        Optional<Cargo> cargo = cargoService.obtenerCargosPorId(id);
        return cargo.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CargoDTO> actualizarCargo(@PathVariable int id, @Validated @RequestBody CargoDTO cargoDTO) {
        CargoDTO actualizado  = cargoService.actualizarCargo(id, cargoDTO);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> existeCargoPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(cargoService.existeCargoPorNombre(nombre));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CargoDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(cargoService.buscarCargoPorNombre(nombre));
    }
}