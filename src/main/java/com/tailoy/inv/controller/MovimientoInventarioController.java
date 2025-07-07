package com.tailoy.inv.controller;

import com.tailoy.inv.dto.MovimientoInventarioDetalleDTO;
import com.tailoy.inv.model.MovimientoInventario;
import com.tailoy.inv.service.MovimientoInventarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService service;

    @GetMapping("/movimientos")
    @PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE_DE_COMPRAS')")
    public ResponseEntity<List<MovimientoInventarioDetalleDTO>> listar() {
        List<MovimientoInventarioDetalleDTO> movimientos = service.listarMovimientos();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<MovimientoInventario>> filtrarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {

        return ResponseEntity.ok(service.filtrarPorRangoFechas(inicio, fin));
    }

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE_DE_COMPRAS')")
    public ResponseEntity<List<MovimientoInventario>> filtrarPorUsuario(@PathVariable int usuarioId) {
        return ResponseEntity.ok(service.filtrarPorUsuario(usuarioId));
    }

    @GetMapping("/producto/{productoId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE_DE_COMPRAS')")
    public ResponseEntity<List<MovimientoInventario>> filtrarPorProducto(@PathVariable int productoId) {
        return ResponseEntity.ok(service.filtrarPorProducto(productoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> obtenerDetalle(@PathVariable int id) {
        return ResponseEntity.ok(service.obtenerDetalleMovimiento(id));
    }

    @GetMapping("/exportar/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE_DE_COMPRAS')")
    public ResponseEntity<byte[]> exportarMovimiento(@PathVariable int id) {
        byte[] excel = service.exportarMovimientos(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=movimiento_" + id + ".xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);
    }
}