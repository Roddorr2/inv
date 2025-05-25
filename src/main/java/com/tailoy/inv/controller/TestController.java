package com.tailoy.inv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("Hola ADMIN, acceso correcto");
    }

    @GetMapping("/compras")
    public ResponseEntity<String> compras() {
        return ResponseEntity.ok("Hola GERENTE DE COMPRAS, acceso correcto");
    }

    @GetMapping("/almacen")
    public ResponseEntity<String> almacen() {
        return ResponseEntity.ok("Hola ALMACENERO, acceso correcto");
    }

    @GetMapping("/recibo")
    public ResponseEntity<String> recibo() {
        return ResponseEntity.ok("Hola ASISTENTE DE RECIBO, acceso correcto");
    }
}
