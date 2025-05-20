package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.dto.UsuarioDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.repository.CargoRepository;
import com.tailoy.inv.repository.UsuarioRepository;
import com.tailoy.inv.service.UsuarioService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
    private UsuarioRepository repo;

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    @Transactional
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        Cargo cargo = cargoRepository.findById(usuarioDTO.getCargo().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado con ID: " + usuarioDTO.getCargo().getId()));

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContrasena(usuarioDTO.getContrasena());
        usuario.setCargo(cargo);
        usuario.setEstado(usuarioDTO.isEstado());

        Usuario guardado = repo.save(usuario);
        return new UsuarioDTO(guardado);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return repo.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(int id) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return new UsuarioDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTO actualizarUsuario(int id, UsuarioDTO usuarioDTO) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContrasena(usuarioDTO.getContrasena());

        Cargo cargo = cargoRepository.findById(usuarioDTO.getCargo().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado con ID: " + usuarioDTO.getCargo().getId()));
        usuario.setCargo(cargo);
        usuario.setEstado(usuarioDTO.isEstado());

        Usuario actualizado = repo.save(usuario);
        return new UsuarioDTO(actualizado);
    }

    @Override
    @Transactional
    public void cambiarEstadoUsuario(int id, boolean estado) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        usuario.setEstado(estado);
        repo.save(usuario);
    }
}
