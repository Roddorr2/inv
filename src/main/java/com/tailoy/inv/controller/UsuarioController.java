package com.tailoy.inv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.tailoy.inv.dto.UsuarioDTO;
import com.tailoy.inv.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@Validated @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO nuevoUsuario = usuarioService.registrarUsuario(usuarioDTO);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable int id, @Validated @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO actualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cambiarEstado(@PathVariable int id, @RequestParam boolean estado) {
        usuarioService.cambiarEstadoUsuario(id, estado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> buscarPorNombreOCorreo(@RequestParam String q) {
        return ResponseEntity.ok(usuarioService.buscarPorNombreOCorreo(q));
    }

    @GetMapping("/existe/nombre")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> existePorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(usuarioService.existePorNombre(nombre));
    }

    @GetMapping("/existe/correo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> existePorCorreo(@RequestParam String correo) {
        return ResponseEntity.ok(usuarioService.existePorCorreo(correo));
    }

    @GetMapping("/cargo/{idCargo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> listarPorCargo(@PathVariable int idCargo) {
        return ResponseEntity.ok(usuarioService.listarUsuarioPorCargo(idCargo));
    }
}
