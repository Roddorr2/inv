package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.UsuarioDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.template.UsuarioOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ActualizarUsuarioCommand extends UsuarioOperacionTemplate implements UsuarioCommand {

    @Override
    protected void validar(UsuarioDTO dto, Integer id) {
        usuarioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    protected Usuario construir(UsuarioDTO dto, Integer id) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Cargo cargo = cargoRepo.findById(dto.getCargo().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        usuario.setCargo(cargo);
        usuario.setEstado(dto.isEstado());
        return usuario;
    }
}
