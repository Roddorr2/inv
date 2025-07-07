package com.tailoy.inv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.MovimientoAlmacen;

@Repository
public interface MovimientoAlmacenRepository extends JpaRepository<MovimientoAlmacen, Integer> {
    List<MovimientoAlmacen> findByTipoAlmacen(int tipoAlmacen);

    @Query(value = """
            SELECT
                ma.id AS [ID],
                p.nombre AS [Producto],
                p.codigo AS [Código],
                map.cantidad AS [Cantidad],
                CASE ma.tipo_almacen
                    WHEN 0 THEN 'SALIDA'
                    WHEN 1 THEN 'ENTRADA'
                END AS [Tipo de almacén]
            FROM movimiento_almacen ma
            JOIN movimiento_almacen_producto map ON ma.id = map.movimiento_almacen_id
            JOIN producto p ON map.producto_id = p.id;
            """, nativeQuery = true)
    List<Object[]> findMovimientoAlmacenDetalle();

}
