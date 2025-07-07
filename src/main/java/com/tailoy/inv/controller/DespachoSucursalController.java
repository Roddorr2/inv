package com.tailoy.inv.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tailoy.inv.dto.DespachoSucursalDTO;
import com.tailoy.inv.dto.DespachoSucursalDetalladoDTO;
import com.tailoy.inv.model.DespachoSucursal;
import com.tailoy.inv.service.DespachoSucursalService;

@RestController
@RequestMapping("api/despachos")
public class DespachoSucursalController {

    @Autowired
    private DespachoSucursalService despachoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DespachoSucursal> registrarDespacho(@RequestBody DespachoSucursalDTO dto) {
        DespachoSucursal despacho = despachoService.registrarDespacho(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(despacho);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DespachoSucursal> actualizarDespacho(@PathVariable int id,
            @RequestBody DespachoSucursalDTO dto) {
        dto.setId(id);
        DespachoSucursal despacho = despachoService.actualizarDespacho(dto);
        return ResponseEntity.ok(despacho);
    }

    @GetMapping("/todos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DespachoSucursalDetalladoDTO>> listar() {
        List<DespachoSucursalDetalladoDTO> despachos = despachoService.listarDespachos();
        return ResponseEntity.ok(despachos);
    }

    @GetMapping("{id}")
    public ResponseEntity<DespachoSucursal> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(despachoService.obtenerPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<DespachoSucursal>> listarPorEstado(@PathVariable int estado) {
        return ResponseEntity.ok(despachoService.listarPorEstado(estado));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DespachoSucursal>> buscarPorFechas(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate hasta) {
        return ResponseEntity.ok(despachoService.buscarPorFechas(desde, hasta));
    }

    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> actualizarEstado(@PathVariable int id, @RequestParam int estado) {
        despachoService.actualizarEstado(id, estado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exportar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> exportarDespacho(@PathVariable int id) {
        byte[] excelData = despachoService.exportarDespacho(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("despacho_a_sucursal" + id + ".xlsx")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelData);
    }

}
