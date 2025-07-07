package com.tailoy.inv.command;

import com.tailoy.inv.dto.SubcategoriaDTO;

public interface SubcategoriaCommand {
    SubcategoriaDTO ejecutar(SubcategoriaDTO dto, Integer id);
}
