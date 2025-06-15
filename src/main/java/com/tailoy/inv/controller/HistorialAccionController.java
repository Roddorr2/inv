package com.tailoy.inv.controller;

import com.tailoy.inv.model.HistorialAccion;
import com.tailoy.inv.service.HistorialAccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialAccionController {

    @Autowired
    private HistorialAccionService historialService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarAccion(@RequestParam String nombreUsuario,
                                                  @RequestParam int tipoAccion,
                                                  @RequestParam String descripcion,
                                                  @RequestParam int modulo) {
        historialService.registrarAccion(nombreUsuario, tipoAccion, descripcion, modulo);
        return ResponseEntity.ok("Acción registrada con éxito.");
    }

    @GetMapping("/todos")
    public ResponseEntity<List<HistorialAccion>> obtenerTodos() {
        return ResponseEntity.ok(historialService.obtenerHistorialCompleto());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<HistorialAccion>> buscarPorUsuarioYTipo(
            @RequestParam String nombreUsuario,
            @RequestParam int tipoAccion) {
        return ResponseEntity.ok(historialService.buscarHistorial(nombreUsuario, tipoAccion));
    }

    @GetMapping("/buscar/avanzado")
    public ResponseEntity<List<HistorialAccion>> buscarAvanzado(@RequestParam String nombreUsuario, @RequestParam int tipoAccion, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin, @RequestParam int modulo) {
        return ResponseEntity.ok(historialService.buscarHistorial(nombreUsuario, tipoAccion, fechaInicio, fechaFin, modulo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialAccion> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(historialService.obtenerPorId(id));
    }
}
