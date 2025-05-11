package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.model.MovimientoAlmacen;

public interface MovimientoAlmacenService {
    MovimientoAlmacen registrarEntrada(MovimientoAlmacen movimientoAlmacen);
    MovimientoAlmacen registrarSalida(MovimientoAlmacen movimientoAlmacen);
    List<MovimientoAlmacen> listarMovimientosPorTipo(int tipoAlmacen);
}
