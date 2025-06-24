package com.tailoy.inv.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tailoy.inv.dto.OrdenCompraDTO;
import com.tailoy.inv.dto.OrdenCompraDetalladoDTO;
import com.tailoy.inv.model.OrdenCompra;
import com.tailoy.inv.service.OrdenCompraService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/ordenes")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @PostMapping
    public ResponseEntity<OrdenCompra> registrar(@RequestBody OrdenCompraDTO dto) {
        OrdenCompra orden = ordenCompraService.registrarOrdenCompra(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompra> actualizar(@PathVariable int id, @RequestBody OrdenCompraDTO dto) {
        dto.setId(id);
        OrdenCompra orden = ordenCompraService.actualizarOrdenCompra(dto);
        return ResponseEntity.ok(orden);
    }
    
    @GetMapping("/todas")
    public ResponseEntity<List<OrdenCompraDetalladoDTO>> listar() {
            List<OrdenCompraDetalladoDTO> ordenes = ordenCompraService.listarOrdenes();
            return ResponseEntity.ok(ordenes);
        }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(ordenCompraService.obtenerPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenCompra>> listarPorEstado(@PathVariable int estado) {
        return ResponseEntity.ok(ordenCompraService.listarPorEstado(estado));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<OrdenCompra>> buscarPorFechas(
        @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
        @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {
        return ResponseEntity.ok(ordenCompraService.buscarPorFechas(desde, hasta));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> actualizarEstado(@PathVariable int id, @RequestParam int estado) {
        ordenCompraService.actualizarEstado(id, estado);
        return ResponseEntity.noContent().build();
    }

}
