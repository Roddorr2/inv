package com.tailoy.inv.command;

import com.tailoy.inv.dto.SucursalDTO;

public interface SucursalCommand {
    SucursalDTO ejecutar(SucursalDTO dto, Integer id);
}
