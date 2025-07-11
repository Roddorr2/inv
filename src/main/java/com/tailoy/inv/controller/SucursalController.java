package com.tailoy.inv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.tailoy.inv.dto.SucursalDTO;
import com.tailoy.inv.service.impl.SucursalServiceImpl;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SucursalController {

    private final SucursalServiceImpl sucursalService;

    public SucursalController(SucursalServiceImpl sucursalService) {
        this.sucursalService = sucursalService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SucursalDTO> registrarSucursal(@Validated @RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO nuevaSucursal = sucursalService.registrarSucursal(sucursalDTO);
        return ResponseEntity.ok(nuevaSucursal);
    }

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> listarSucursales() {
        return ResponseEntity.ok(sucursalService.listarSucursales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> obtenerSucursalPorId(@PathVariable int id) {
        return ResponseEntity.ok(sucursalService.obtenerSucursalPorId(id));
    }

    @GetMapping("/correo")
    public ResponseEntity<List<SucursalDTO>> obtenerSucursalPorCorreo(@RequestParam String correo) {
        return ResponseEntity.ok(sucursalService.obtenerSucursalPorCorreo(correo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SucursalDTO> actualizarSucursal(@PathVariable int id, @Validated @RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO actualizada = sucursalService.actualizarSucursal(id, sucursalDTO);
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<SucursalDTO>> buscarPorDireccionOCorreo(@RequestParam String q) {
        return ResponseEntity.ok(sucursalService.buscarPorDireccionOCorreo(q));
    }
    
    @GetMapping("/existe")
    public ResponseEntity<Boolean> existePorCorreo(@RequestParam String correo) {
        return ResponseEntity.ok(sucursalService.existeSucursalPorCorreo(correo));
    }
}
