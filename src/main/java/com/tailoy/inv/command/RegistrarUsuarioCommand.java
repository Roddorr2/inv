package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.UsuarioDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.template.UsuarioOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class RegistrarUsuarioCommand extends UsuarioOperacionTemplate implements UsuarioCommand {

    @Override
    protected void validar(UsuarioDTO dto, Integer id) {
        if (usuarioRepo.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }
        if (usuarioRepo.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre.");
        }
    }

    @Override
    protected Usuario construir(UsuarioDTO dto, Integer id) {
        Cargo cargo = cargoRepo.findById(dto.getCargo().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        usuario.setCargo(cargo);
        usuario.setEstado(dto.isEstado());
        return usuario;
    }
}
