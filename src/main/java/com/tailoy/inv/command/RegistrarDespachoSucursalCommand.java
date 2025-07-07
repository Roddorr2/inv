package com.tailoy.inv.command;

import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.DespachoSucursalDTO;
import com.tailoy.inv.model.DespachoSucursal;
import com.tailoy.inv.model.DespachoSucursalProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Sucursal;
import com.tailoy.inv.template.DespachoSucursalOperacionTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegistrarDespachoSucursalCommand extends DespachoSucursalOperacionTemplate
        implements DespachoSucursalCommand {

    @Override
    protected void validar(DespachoSucursalDTO dto) {
        if (dto.getSucursal() == null || dto.getSucursal().getId() == 0) {
            throw new IllegalArgumentException("Sucursal inválida");
        }
    }

    @Override
    protected DespachoSucursal procesarDespacho(DespachoSucursalDTO dto) {
        Sucursal sucursal = sucursalRepo.findById(dto.getSucursal().getId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        DespachoSucursal despacho = new DespachoSucursal();
        despacho.setSucursal(sucursal);
        despacho.setFecha(dto.getFecha());
        despacho.setEstadoOperacion(1);

        DespachoSucursal saved = despachoSucursalRepo.save(despacho);
        despachoProductoRepo.flush();

        List<DespachoSucursalProducto> productos = dto.getProductos().stream().map(p -> {
            Producto producto = productoRepo.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            DespachoSucursalProducto dp = new DespachoSucursalProducto();
            dp.setProducto(producto);
            dp.setCantidad(p.getCantidad());
            dp.setPrecioUnitario(p.getPrecioUnitario());
            dp.setObservaciones(p.getObservaciones());
            dp.setDespachoSucursal(saved);
            return dp;
        }).collect(Collectors.toList());

        despachoProductoRepo.saveAll(productos);
        return saved;
    }
}
