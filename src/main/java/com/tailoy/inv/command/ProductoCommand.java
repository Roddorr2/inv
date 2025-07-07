package com.tailoy.inv.command;

import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;

public interface ProductoCommand {
    Producto ejecutar(ProductoDTO dto, Integer id);
}
