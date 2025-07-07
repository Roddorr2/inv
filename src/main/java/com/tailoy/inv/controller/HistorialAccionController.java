package com.tailoy.inv.controller;

import com.tailoy.inv.model.HistorialAccion;
import com.tailoy.inv.service.HistorialAccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialAccionController {

    @Autowired
    private HistorialAccionService historialService;

    @GetMapping("/todos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HistorialAccion>> obtenerTodos() {
        return ResponseEntity.ok(historialService.obtenerHistorialCompleto());
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HistorialAccion>> buscarPorUsuarioYTipo(
            @RequestParam String nombreUsuario,
            @RequestParam int tipoAccion) {
        return ResponseEntity.ok(historialService.buscarHistorial(nombreUsuario, tipoAccion));
    }

    @GetMapping("/buscar/avanzado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HistorialAccion>> buscarAvanzado(@RequestParam String nombreUsuario,
            @RequestParam int tipoAccion,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            @RequestParam int modulo) {
        return ResponseEntity
                .ok(historialService.buscarHistorial(nombreUsuario, tipoAccion, fechaInicio, fechaFin, modulo));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HistorialAccion> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(historialService.obtenerPorId(id));
    }
}
