package com.tailoy.inv.service;

import java.time.LocalDate;
import java.util.List;

import com.tailoy.inv.dto.MovimientoInventarioDetalleDTO;
import com.tailoy.inv.model.MovimientoInventario;

public interface MovimientoInventarioService {
    List<MovimientoInventarioDetalleDTO> listarMovimientos();

    List<MovimientoInventario> filtrarPorRangoFechas(LocalDate inicio, LocalDate fin);

    List<MovimientoInventario> filtrarPorUsuario(int usuarioId);

    List<MovimientoInventario> filtrarPorProducto(int productoId);

    MovimientoInventario obtenerDetalleMovimiento(int id);

    byte[] exportarMovimientos(int idMovimineto);
}
