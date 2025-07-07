package com.tailoy.inv.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.model.HistorialAccion;
import com.tailoy.inv.repository.HistorialAccionRepository;
import com.tailoy.inv.service.HistorialAccionService;

@Service
public class HistorialAccionServiceImpl implements HistorialAccionService {
    @Autowired
    private HistorialAccionRepository historialAccionRepo;

    @Auditable(accion = "Revisión de auditoría", tipo = TipoAccionEnum.CONSULTA, modulo = ModuloEnum.HISTORIAL)
    @Override
    public List<HistorialAccion> obtenerHistorialCompleto() {
        return historialAccionRepo.findAll();
    }

    @Override
    public List<HistorialAccion> buscarHistorial(String nombreUsuario, int tipoAccion) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío.");
        }
        return historialAccionRepo.findByUsuarioNombreAndTipoAccion(nombreUsuario, tipoAccion);
    }

    @Override
    public List<HistorialAccion> buscarHistorial(String nombreUsuario, int tipoAccion, LocalDateTime fechaInicio,
            LocalDateTime fechaFin, int modulo) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }

        return historialAccionRepo.findByUsuarioNombreAndTipoAccionAndFechaBetweenAndModulo(nombreUsuario, tipoAccion,
                fechaInicio, fechaFin, modulo);
    }

    @Override
    public HistorialAccion obtenerPorId(int id) {
        return historialAccionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial con ID " + id + " no encontrado."));
    }

}
