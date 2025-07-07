package com.tailoy.inv.service;

import java.time.LocalDateTime;
import java.util.List;
import com.tailoy.inv.model.HistorialAccion;

public interface HistorialAccionService {
    List<HistorialAccion> obtenerHistorialCompleto();
    List<HistorialAccion> buscarHistorial(String nombreUsuario, int tipoAccion);
    List<HistorialAccion> buscarHistorial(String nombreUsuario, int tipoAccion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int modulo);
    HistorialAccion obtenerPorId(int id);
}
