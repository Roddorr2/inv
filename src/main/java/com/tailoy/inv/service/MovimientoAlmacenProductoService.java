package com.tailoy.inv.service;

import java.util.List;

import com.tailoy.inv.model.MovimientoAlmacenProducto;

public interface MovimientoAlmacenProductoService {
    MovimientoAlmacenProducto registrarProductoMovimiento(MovimientoAlmacenProducto movimientoProducto);
    List<MovimientoAlmacenProducto> obtenerPorMovimiento(int movimientoAlmacenId);
    void anularProductosPorMovimiento(int movimientoAlmacenId);
    List<MovimientoAlmacenProducto> obtenerActivosPorMovimiento(int movimientoAlmacenId);    
}
