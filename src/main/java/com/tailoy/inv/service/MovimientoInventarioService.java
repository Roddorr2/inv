package com.tailoy.inv.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tailoy.inv.model.MovimientoInventario;

public interface MovimientoInventarioService {
    void registrarAjusteStock(int productoId, int cantidad, String motivo, int tipoMovimiento, int usuarioId);
    List<MovimientoInventario> listarMovimientos();
    List<MovimientoInventario> filtrarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin);
    List<MovimientoInventario> filtrarPorUsuario(int usuarioId);
    List<MovimientoInventario> filtrarPorProducto(int productoId); 
    MovimientoInventario obtenerDetalleMovimiento(int id);
    byte[] exportarMovimientos(List<MovimientoInventario> movimientos, String formato);
}
