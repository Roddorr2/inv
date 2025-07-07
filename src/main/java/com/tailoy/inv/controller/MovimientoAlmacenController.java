package com.tailoy.inv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tailoy.inv.dto.MovimientoAlmacenDetalleDTO;
import com.tailoy.inv.model.MovimientoAlmacen;
import com.tailoy.inv.service.MovimientoAlmacenService;

@RestController
@RequestMapping("/api/almacenes")
public class MovimientoAlmacenController {
    @Autowired
    private MovimientoAlmacenService movimientoAlmacenService;

    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimientoAlmacenDetalleDTO>> listarAlmacenes() {
        List<MovimientoAlmacenDetalleDTO> movimientos = movimientoAlmacenService.listarMovimientos();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/tipo/{tipoAlmacen}")
    public ResponseEntity<List<MovimientoAlmacen>> listarPorTipo(@PathVariable int tipoAlmacen) {
        List<MovimientoAlmacen> movimientos = movimientoAlmacenService.listarMovimientosPorTipo(tipoAlmacen);
        return ResponseEntity.ok(movimientos);
    }
}
