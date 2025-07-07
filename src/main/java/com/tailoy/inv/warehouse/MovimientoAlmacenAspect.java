package com.tailoy.inv.warehouse;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tailoy.inv.model.DespachoSucursalProducto;
import com.tailoy.inv.model.MovimientoAlmacen;
import com.tailoy.inv.model.MovimientoAlmacenProducto;
import com.tailoy.inv.model.OrdenCompraProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.repository.DespachoSucursalProductoRepository;
import com.tailoy.inv.repository.MovimientoAlmacenProductoRepository;
import com.tailoy.inv.repository.MovimientoAlmacenRepository;
import com.tailoy.inv.repository.OrdenCompraProductoRepository;

@Aspect
@Component
public class MovimientoAlmacenAspect {
    @Autowired
    private MovimientoAlmacenRepository movimientoAlmacenRepo;

    @Autowired
    private MovimientoAlmacenProductoRepository movimientoAlmacenProductoRepo;

    @Autowired
    private DespachoSucursalProductoRepository despachoProductoRepo;

    @Autowired
    private OrdenCompraProductoRepository ordenProductoRepo;

    @Around("@annotation(almacen)")
    public Object interceptarMovimiento(ProceedingJoinPoint joinPoint, Almacen almacen) throws Throwable {
        Object result = joinPoint.proceed();

        Object[] args = joinPoint.getArgs();
        int id = (int) args[0];
        int nuevoEstado = (int) args[1];

        if (nuevoEstado == 2) {
            MovimientoAlmacen movimiento = new MovimientoAlmacen();
            movimiento.setTipoAlmacen(almacen.tipoAlmacen().getTipo());
            movimiento = movimientoAlmacenRepo.save(movimiento);

            if (almacen.tipoAlmacen() == TipoAlmacenEnum.SALIDA) {
                List<DespachoSucursalProducto> productos = despachoProductoRepo.findByDespachoSucursalId(id);

                for (DespachoSucursalProducto dsp : productos) {
                    Producto producto = dsp.getProducto();
                    int cantidad = dsp.getCantidad();

                    MovimientoAlmacenProducto map = new MovimientoAlmacenProducto();
                    map.setCantidad(cantidad);
                    map.setMovimientoAlmacen(movimiento);
                    map.setProducto(producto);
                    movimientoAlmacenProductoRepo.save(map);
                }
            }

            if (almacen.tipoAlmacen() == TipoAlmacenEnum.ENTRADA) {
                List<OrdenCompraProducto> productos = ordenProductoRepo.findByOrdenCompraId(id);

                for (OrdenCompraProducto ocp : productos) {
                    Producto producto = ocp.getProducto();
                    int cantidad = ocp.getCantidad();

                    MovimientoAlmacenProducto map = new MovimientoAlmacenProducto();
                    map.setCantidad(cantidad);
                    map.setMovimientoAlmacen(movimiento);
                    map.setProducto(producto);
                    movimientoAlmacenProductoRepo.save(map);
                }
            }
        }

        return result;
    }

}
