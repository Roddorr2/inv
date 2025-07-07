package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.command.ActualizarUsuarioCommand;
import com.tailoy.inv.command.CambiarEstadoUsuarioCommand;
import com.tailoy.inv.command.RegistrarUsuarioCommand;
import com.tailoy.inv.dto.UsuarioDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.repository.CargoRepository;
import com.tailoy.inv.repository.UsuarioRepository;
import com.tailoy.inv.service.UsuarioService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private RegistrarUsuarioCommand registrarUsuarioCommand;

    @Autowired
    private ActualizarUsuarioCommand actualizarUsuarioCommand;

    @Autowired
    private CambiarEstadoUsuarioCommand cambiarEstadoUsuarioCommand;

    @Auditable(accion = "Registro de usuarios", tipo = TipoAccionEnum.REGISTRO, modulo = ModuloEnum.USUARIO)
    @Override
    @Transactional
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario u = registrarUsuarioCommand.ejecutar(usuarioDTO, null);
        return new UsuarioDTO(u);
    }

    @Auditable(accion = "Modificación de usuarios", tipo = TipoAccionEnum.MODIFICACION, modulo = ModuloEnum.USUARIO)
    @Override
    @Transactional
    public UsuarioDTO actualizarUsuario(int id, UsuarioDTO usuarioDTO) {
        Usuario u = actualizarUsuarioCommand.ejecutar(usuarioDTO, id);
        return new UsuarioDTO(u);
    }

    @Auditable(accion = "Activación/desactivación de usuarios", tipo = TipoAccionEnum.CAMBIO_ESTADO, modulo = ModuloEnum.USUARIO)
    @Override
    @Transactional
    public void cambiarEstadoUsuario(int id, boolean estado) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEstado(estado);
        cambiarEstadoUsuarioCommand.ejecutar(dto, id);
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
    public List<UsuarioDTO> buscarPorNombreOCorreo(String q) {
        List<UsuarioDTO> usuarios = repo.findByNombreOrCorreoContainingIgnoreCase(q.trim());
        return usuarios;
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return repo.existsByNombre(nombre);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return repo.existsByCorreo(correo);
    }

    @Override
    public List<UsuarioDTO> listarUsuarioPorCargo(int idCargo) {
        Cargo cargo = cargoRepository.findById(idCargo)
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrad con ID: " + idCargo));

        List<UsuarioDTO> usuarios = repo.findByCargo(cargo);
        return usuarios;
    }

    @Override
    public UsuarioDTO obtenerPorNombre(String nombre) {
        Usuario usuario = repo.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + nombre));
        return new UsuarioDTO(usuario);
    }
}
