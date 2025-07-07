package com.tailoy.inv.command;

import com.tailoy.inv.dto.CargoDTO;

public interface CargoCommand {
    CargoDTO ejecutar(CargoDTO dto, Integer id);
}
