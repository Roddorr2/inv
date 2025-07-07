package com.tailoy.inv.command;

import com.tailoy.inv.dto.DespachoSucursalDTO;
import com.tailoy.inv.model.DespachoSucursal;

public interface DespachoSucursalCommand {
    DespachoSucursal ejecutar(DespachoSucursalDTO dto);
}
