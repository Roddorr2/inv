package com.tailoy.inv.command;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.OrdenCompraDTO;
import com.tailoy.inv.model.OrdenCompra;
import com.tailoy.inv.model.OrdenCompraProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.template.OrdenCompraOperacionTemplate;

@Component
public class RegistrarOrdenCompraCommand extends OrdenCompraOperacionTemplate implements OrdenCompraCommand {

    @Override
    protected void validar(OrdenCompraDTO dto) {
        if (Objects.isNull(dto.getProveedor()) || dto.getProveedor().getId() == 0) {
            throw new IllegalArgumentException("Proveedor inválido.");
        }
        if (dto.getProductos() == null || dto.getProductos().isEmpty()) {
            throw new IllegalArgumentException("Debe agregar al menos un producto.");
        }
    }

    @Override
    protected OrdenCompra procesarOrden(OrdenCompraDTO dto) {
        Proveedor proveedor = proveedorRepo.findById(dto.getProveedor().getId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado."));

        OrdenCompra orden = new OrdenCompra();
        orden.setProveedor(proveedor);
        orden.setFecha(dto.getFecha());
        orden.setEstadoOperacion(1);

        OrdenCompra ordenGuardada = ordenCompraRepo.save(orden);

        List<OrdenCompraProducto> productos = dto.getProductos().stream().map(p -> {
            Producto producto = productoRepo.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado."));

            OrdenCompraProducto ocp = new OrdenCompraProducto();
            ocp.setProducto(producto);
            ocp.setCantidad(p.getCantidad());
            ocp.setPrecioUnitario(p.getPrecioUnitario());
            ocp.setObservaciones(p.getObservaciones());
            ocp.setOrdenCompra(ordenGuardada);
            return ocp;
        }).collect(Collectors.toList());

        ordenProductoRepo.saveAll(productos);
        return ordenGuardada;
    }
}
