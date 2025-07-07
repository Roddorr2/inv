package com.tailoy.inv.command;

import com.tailoy.inv.dto.ProveedorDTO;
import com.tailoy.inv.model.Proveedor;

public interface ProveedorCommand {
    Proveedor ejecutar(ProveedorDTO dto, Integer id);
}
