package com.tailoy.inv.command;

import com.tailoy.inv.dto.UsuarioDTO;
import com.tailoy.inv.model.Usuario;

public interface UsuarioCommand {
    Usuario ejecutar(UsuarioDTO dto, Integer id);
}