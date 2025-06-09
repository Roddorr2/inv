package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.dto.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO obtenerUsuarioPorId(int id);
    UsuarioDTO actualizarUsuario(int id, UsuarioDTO usuarioDTO);
    void cambiarEstadoUsuario(int id, boolean estado);
    List<UsuarioDTO> buscarPorNombreOCorreo(String q);
    boolean existePorNombre(String nombre);
    boolean existePorCorreo(String correo);
    List<UsuarioDTO> listarUsuarioPorCargo(int id);
}
