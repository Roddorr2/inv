package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.dto.MovimientoAlmacenDetalleDTO;
import com.tailoy.inv.model.MovimientoAlmacen;
import com.tailoy.inv.repository.MovimientoAlmacenRepository;
import com.tailoy.inv.service.MovimientoAlmacenService;

@Service
public class MovimientoAlmacenServiceImpl implements MovimientoAlmacenService {
    @Autowired
    private MovimientoAlmacenRepository repo;

    @Override
    public List<MovimientoAlmacenDetalleDTO> listarMovimientos() {
        List<Object[]> results = repo.findMovimientoAlmacenDetalle();

        return results.stream()
                .map(row -> new MovimientoAlmacenDetalleDTO(
                        (int) row[0],
                        (String) row[1],
                        (int) row[2],
                        (int) row[3],
                        (String) row[4]))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoAlmacen> listarMovimientosPorTipo(int tipoAlmacen) {
        if (tipoAlmacen <= 0) {
            throw new IllegalArgumentException("Debe especificarse un tipo de movimiento de almacén válido.");
        }
        return repo.findByTipoAlmacen(tipoAlmacen);

    }
}
