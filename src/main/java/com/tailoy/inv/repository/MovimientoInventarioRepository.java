package com.tailoy.inv.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.MovimientoInventario;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {

    @Query(value = """
            SELECT
                mi.id AS [ID de movimiento],
                mi.cantidad AS [Cantidad],
                CASE mi.tipo_movimiento
                    WHEN 0 THEN 'CANCELACION_OC'
                    WHEN 1 THEN 'REGISTRO_OC'
                    WHEN 2 THEN 'APROBACION_OC'
                    WHEN 3 THEN 'RECHAZO_OC'
                    WHEN 4 THEN 'ACTUALIZACION_OC'
                    WHEN 5 THEN 'CANCELACION_DS'
                    WHEN 6 THEN 'REGISTRO_DS'
                    WHEN 7 THEN 'APROBACION_DS'
                    WHEN 8 THEN 'RECHAZO_DS'
                    WHEN 9 THEN 'ACTUALIZACION_DS'
                END AS [Tipo de movimiento],
                mi.fecha AS [Fecha],
                p.nombre AS [Producto],
                u.nombre AS [Usuario]
            FROM movimiento_inventario mi
            JOIN producto p ON mi.producto_id = p.id
            JOIN usuario u ON mi.usuario_id = u.id;
            """, nativeQuery = true)
    List<Object[]> findMovimientos();

    List<MovimientoInventario> findByFechaBetween(LocalDate inicio, LocalDate fin);

    List<MovimientoInventario> findByUsuarioId(int usuarioId);

    List<MovimientoInventario> findByProductoId(int productoId);
}
