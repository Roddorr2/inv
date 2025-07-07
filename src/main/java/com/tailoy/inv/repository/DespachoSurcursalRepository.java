package com.tailoy.inv.repository;

import com.tailoy.inv.model.DespachoSucursal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DespachoSurcursalRepository extends JpaRepository<DespachoSucursal, Integer> {
    List<DespachoSucursal> findByEstadoOperacion(int estadoOperacion);
    List<DespachoSucursal> findByFechaBetween(LocalDate desde, LocalDate hasta);

    @Query(value = """
        SELECT
            ds.id AS [ID],
            s.correo AS [Correo de sucursal],
            ds.fecha AS [Fecha],
            CASE ds.estado_operacion
                WHEN 0 THEN 'Cancelado'
                WHEN 1 THEN 'Pendiente'
                WHEN 2 THEN 'Aprobado'
                WHEN 3 THEN 'Rechazado'
            END AS [Estado Operación],
            p.nombre AS [Producto],
            dsp.precio_unitario AS [Precio],
            dsp.cantidad AS [Cantidad],
            ROUND(dsp.precio_unitario * dsp.cantidad, 2) AS [Total],
            dsp.observaciones AS [Observaciones]
        FROM despacho_sucursal AS ds
        JOIN despacho_sucursal_producto dsp ON ds.id = dsp.despacho_sucursal_id
        JOIN sucursal s ON ds.sucursal_id = s.id
        JOIN producto p ON dsp.producto_id = p.id
        ORDER BY (dsp.precio_unitario * dsp.cantidad) DESC
            """, nativeQuery = true)
    List<Object[]> findDespachoSucursalDetalle();
}
