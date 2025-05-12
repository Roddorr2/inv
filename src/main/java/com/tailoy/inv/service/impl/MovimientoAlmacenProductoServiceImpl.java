package com.tailoy.inv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tailoy.inv.model.MovimientoAlmacenProducto;
import com.tailoy.inv.repository.MovimientoAlmacenProductoRepository;
import com.tailoy.inv.service.MovimientoAlmacenProductoService;

@Service
public class MovimientoAlmacenProductoServiceImpl implements MovimientoAlmacenProductoService {
    private final MovimientoAlmacenProductoRepository repo;

    public MovimientoAlmacenProductoServiceImpl(MovimientoAlmacenProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public MovimientoAlmacenProducto registrarProductoMovimiento(MovimientoAlmacenProducto movimientoProducto) {
        if (movimientoProducto == null) {
            throw new IllegalArgumentException("El movimiento de producto es obligatorio.");
        }

        if (movimientoProducto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }

        if (movimientoProducto.getMovimientoAlmacen() == null || movimientoProducto.getMovimientoAlmacen().getId() <= 0) {
            throw new IllegalArgumentException("Debe especificarse un movimiento de almacén válido.");
        }

        if (movimientoProducto.getProducto() == null || movimientoProducto.getProducto().getId() <= 0) {
            throw new IllegalArgumentException("Debe especificarse un producto válido.");
        }

        return repo.save(movimientoProducto);
    }

    @Override
    public List<MovimientoAlmacenProducto> obtenerPorMovimiento(int movimientoAlmacenId) {
        if (movimientoAlmacenId <= 0) {
            throw new IllegalArgumentException("ID de movimiento inválido.");
        }

        return repo.findByMovimientoAlmacenId(movimientoAlmacenId);
    }

    @Override
    public void anularProductosPorMovimiento(int movimientoAlmacenId) {
        if (movimientoAlmacenId <= 0) {
            throw new IllegalArgumentException("ID de movimiento inválido para anular");
        }
        
        List<MovimientoAlmacenProducto> productos = repo.findByMovimientoAlmacenId(movimientoAlmacenId);
        
        for (MovimientoAlmacenProducto p : productos) {
            p.setCantidad(0);
            repo.save(p);
        }
    }

    @Override
    public List<MovimientoAlmacenProducto> obtenerActivosPorMovimiento(int movimientoAlmacenId) {
        if (movimientoAlmacenId <= 0) {
            throw new IllegalArgumentException("ID de movimiento inválido.");
        }

        return repo.findByMovimientoAlmacenIdAndCantidadGreaterThan(movimientoAlmacenId, 0);
    }
}
