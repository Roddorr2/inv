package com.tailoy.inv.command;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.OrdenCompraDTO;
import com.tailoy.inv.dto.ProductoOrdenDTO;
import com.tailoy.inv.model.OrdenCompra;
import com.tailoy.inv.model.OrdenCompraProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.template.OrdenCompraOperacionTemplate;

@Component
public class ActualizarOrdenCompraCommand extends OrdenCompraOperacionTemplate implements OrdenCompraCommand {

    @Override
    protected void validar(OrdenCompraDTO dto) {
        if (dto.getId() <= 0) {
            throw new IllegalArgumentException("ID de orden inválido.");
        }
        if (Objects.isNull(dto.getProveedor()) || dto.getProveedor().getId() <= 0) {
            throw new IllegalArgumentException("Proveedor inválido.");
        }
        if (dto.getProductos() == null || dto.getProductos().isEmpty()) {
            throw new IllegalArgumentException("Debe agregar al menos un producto.");
        }
    }

    @Override
    protected OrdenCompra procesarOrden(OrdenCompraDTO dto) {
        OrdenCompra orden = ordenCompraRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada."));

        Proveedor proveedor = proveedorRepo.findById(dto.getProveedor().getId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado."));

        orden.setProveedor(proveedor);
        orden.setFecha(dto.getFecha());
        orden.setEstadoOperacion(dto.getEstadoOperacion());
        ordenCompraRepo.save(orden);

        Map<Integer, OrdenCompraProducto> actuales = ordenProductoRepo.findByOrdenCompraId(orden.getId()).stream()
                .collect(Collectors.toMap(OrdenCompraProducto::getId, p -> p));

        List<OrdenCompraProducto> actualizados = new ArrayList<>();

        for (ProductoOrdenDTO p : dto.getProductos()) {
            Producto prod = productoRepo.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado."));

            OrdenCompraProducto ocp;
            if (p.getId() != 0 && actuales.containsKey(p.getId())) {
                ocp = actuales.get(p.getId());
                ocp.setCantidad(p.getCantidad());
                ocp.setPrecioUnitario(p.getPrecioUnitario());
                ocp.setObservaciones(p.getObservaciones());
                ocp.setProducto(prod);
                actuales.remove(p.getId());
            } else {
                ocp = new OrdenCompraProducto();
                ocp.setOrdenCompra(orden);
                ocp.setProducto(prod);
                ocp.setCantidad(p.getCantidad());
                ocp.setPrecioUnitario(p.getPrecioUnitario());
                ocp.setObservaciones(p.getObservaciones());
            }
            actualizados.add(ocp);
        }

        actuales.values().forEach(ordenProductoRepo::delete);
        ordenProductoRepo.saveAll(actualizados);

        return orden;
    }
}
