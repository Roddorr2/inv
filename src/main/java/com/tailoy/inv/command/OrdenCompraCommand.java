package com.tailoy.inv.command;

import com.tailoy.inv.dto.OrdenCompraDTO;
import com.tailoy.inv.model.OrdenCompra;

public interface OrdenCompraCommand {
    OrdenCompra ejecutar(OrdenCompraDTO dto);
}
