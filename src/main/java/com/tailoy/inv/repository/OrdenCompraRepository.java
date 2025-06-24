package com.tailoy.inv.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.model.OrdenCompra;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Integer> {
	List<OrdenCompra> findByEstadoOperacion(int estadoOperacion);
	List<OrdenCompra> findByFechaBetween(LocalDateTime desde, LocalDateTime hasta);
	@Query(value = """
		SELECT pr.nombre AS proveedor,
			oc.fecha AS fecha,
			CASE oc.estado_operacion
				WHEN 0 THEN 'Cancelado'
				WHEN 1 THEN 'Pendiente'
				WHEN 2 THEN 'Aprobado'
				WHEN 3 THEN 'Rechazado'
			END AS estadoOperacion,
			p.nombre AS producto,
			ocp.precio_unitario AS precioUnitario,
			ocp.cantidad AS cantidad,
			(ocp.precio_unitario * ocp.cantidad) AS total,
			ocp.observaciones AS observaciones
		FROM orden_compra oc
		JOIN orden_compra_producto ocp ON oc.id = ocp.orden_compra_id
		JOIN proveedor pr ON oc.proveedor_id = pr.id
		JOIN producto p ON ocp.producto_id = p.id
		ORDER BY (ocp.precio_unitario * ocp.cantidad) DESC
		""", nativeQuery = true)
	List<Object[]> findOrdenesCompraDetalle();
}
