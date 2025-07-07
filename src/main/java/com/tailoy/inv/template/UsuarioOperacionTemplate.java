package com.tailoy.inv.template;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.dto.UsuarioDTO;
import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.repository.CargoRepository;
import com.tailoy.inv.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class UsuarioOperacionTemplate {

    @Autowired
    protected UsuarioRepository usuarioRepo;

    @Autowired
    protected CargoRepository cargoRepo;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public final Usuario ejecutar(UsuarioDTO dto, Integer id) {
        validar(dto, id);
        Usuario usuario = construir(dto, id);
        return usuarioRepo.save(usuario);
    }

    protected abstract void validar(UsuarioDTO dto, Integer id);

    protected abstract Usuario construir(UsuarioDTO dto, Integer id);
}
