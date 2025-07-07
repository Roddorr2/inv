package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.DespachoSucursalDTO;
import com.tailoy.inv.dto.ProductoDespachoDTO;
import com.tailoy.inv.model.DespachoSucursal;
import com.tailoy.inv.model.DespachoSucursalProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Sucursal;
import com.tailoy.inv.template.DespachoSucursalOperacionTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ActualizarDespachoSucursalCommand extends DespachoSucursalOperacionTemplate
        implements DespachoSucursalCommand {

    @Override
    protected void validar(DespachoSucursalDTO dto) {
        if (dto.getId() == 0)
            throw new IllegalArgumentException("ID del despacho inválido");
    }

    @Override
    protected DespachoSucursal procesarDespacho(DespachoSucursalDTO dto) {
        DespachoSucursal despacho = despachoSucursalRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado"));

        Sucursal sucursal = sucursalRepo.findById(dto.getSucursal().getId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        despacho.setSucursal(sucursal);
        despacho.setFecha(dto.getFecha());
        despacho.setEstadoOperacion(dto.getEstadoOperacion());

        despachoSucursalRepo.save(despacho);

        Map<Integer, DespachoSucursalProducto> actuales = despachoProductoRepo
                .findByDespachoSucursalId(despacho.getId()).stream()
                .collect(Collectors.toMap(DespachoSucursalProducto::getId, p -> p));

        List<DespachoSucursalProducto> nuevos = new ArrayList<>();

        for (ProductoDespachoDTO p : dto.getProductos()) {
            Producto producto = productoRepo.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DespachoSucursalProducto d;
            if (p.getId() != 0 && actuales.containsKey(p.getId())) {
                d = actuales.get(p.getId());
                actuales.remove(p.getId());
            } else {
                d = new DespachoSucursalProducto();
                d.setDespachoSucursal(despacho);
            }

            d.setProducto(producto);
            d.setCantidad(p.getCantidad());
            d.setPrecioUnitario(p.getPrecioUnitario());
            d.setObservaciones(p.getObservaciones());

            nuevos.add(d);
        }

        despachoProductoRepo.deleteAll(actuales.values());
        despachoProductoRepo.saveAll(nuevos);

        return despacho;
    }
}
