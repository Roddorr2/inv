package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.dto.MovimientoAlmacenDetalleDTO;
import com.tailoy.inv.model.MovimientoAlmacen;

public interface MovimientoAlmacenService {

    List<MovimientoAlmacenDetalleDTO> listarMovimientos();

    List<MovimientoAlmacen> listarMovimientosPorTipo(int tipoAlmacen);
}
