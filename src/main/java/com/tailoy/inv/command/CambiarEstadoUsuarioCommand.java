package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.UsuarioDTO;
import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.template.UsuarioOperacionTemplate;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CambiarEstadoUsuarioCommand extends UsuarioOperacionTemplate implements UsuarioCommand {

    @Override
    protected void validar(UsuarioDTO dto, Integer id) {
        usuarioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @Override
    protected Usuario construir(UsuarioDTO dto, Integer id) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.setEstado(dto.isEstado());
        return usuario;
    }
}
