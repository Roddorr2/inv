package com.tailoy.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.model.MovimientoAlmacen;
import com.tailoy.inv.repository.MovimientoAlmacenRepository;
import com.tailoy.inv.service.MovimientoAlmacenService;

public class MovimientoAlmacenServiceImpl implements MovimientoAlmacenService {
    @Autowired
    private MovimientoAlmacenRepository repo;

    @Override
    public MovimientoAlmacen registrarEntrada(MovimientoAlmacen movimientoAlmacen) {
        if (movimientoAlmacen == null) {
            throw new IllegalArgumentException("El movimiento de almacen es obligatorio.");
        }

        if (movimientoAlmacen.getTipoAlmacen() != 1) {
            throw new IllegalArgumentException("El movimiento de almacen debe ser de entrada.");
        }

        return repo.save(movimientoAlmacen);
    }

    @Override
    public MovimientoAlmacen registrarSalida(MovimientoAlmacen movimientoAlmacen) {
        if (movimientoAlmacen == null) {
            throw new IllegalArgumentException("El movimiento de almacen es obligatorio.");
        }

        if (movimientoAlmacen.getTipoAlmacen() != 2) {
            throw new IllegalArgumentException("El movimiento de almacen debe ser de salida.");
        }

        return repo.save(movimientoAlmacen);
    }

    @Override
    public List<MovimientoAlmacen> listarMovimientosPorTipo(int tipoAlmacen) {
        if (tipoAlmacen <= 0) {
            throw new IllegalArgumentException("Debe especificarse un tipo de movimiento de almacén válido.");
        }
        return repo.findByTipoAlmacen(tipoAlmacen);

    }
}
