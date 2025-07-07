package com.tailoy.inv.command;

import com.tailoy.inv.dto.CategoriaDTO;

public interface CategoriaCommand {
    CategoriaDTO ejecutar(CategoriaDTO dto, Integer id);
}
