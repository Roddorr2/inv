package com.tailoy.inv.service;

import java.time.LocalDate;
import java.util.List;
import com.tailoy.inv.model.HistorialAccion;

public interface HistorialAccionService {
    void registrarAccion(String nombreUsuario, int tipoAccion, String descripcion, int modulo);
    List<HistorialAccion> obtenerHistorialCompleto();
    List<HistorialAccion> buscarHistorial(String nombreUsuario, int tipoAccion);
    List<HistorialAccion> buscarHistorial(String nombreUsuario, int tipoAccion, LocalDate fechaInicio, LocalDate fechaFin, int modulo);
    HistorialAccion obtenerPorId(int id);
}
