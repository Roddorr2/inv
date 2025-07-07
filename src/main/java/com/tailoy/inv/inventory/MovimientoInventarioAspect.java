package com.tailoy.inv.inventory;

import java.time.LocalDate;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tailoy.inv.model.DespachoSucursalProducto;
import com.tailoy.inv.model.MovimientoInventario;
import com.tailoy.inv.model.OrdenCompraProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.repository.DespachoSucursalProductoRepository;
import com.tailoy.inv.repository.MovimientoInventarioRepository;
import com.tailoy.inv.repository.OrdenCompraProductoRepository;
import com.tailoy.inv.repository.UsuarioRepository;

@Aspect
@Component
public class MovimientoInventarioAspect {
    @Autowired
    private MovimientoInventarioRepository inventarioRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private OrdenCompraProductoRepository ordenProductoRepo;

    @Autowired
    private DespachoSucursalProductoRepository despachoProductoRepo;

    @Around("@annotation(Inventario) || @annotation(Movimientos)")
    public Object registrarMovimientoInventario(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = joinPoint.proceed();

        Object[] args = joinPoint.getArgs();
        Integer idDocumento = null;

        for (Object arg : args) {
            if (arg instanceof Integer id) {
                idDocumento = id;
                break;
            } else if (arg instanceof com.tailoy.inv.dto.OrdenCompraDTO dto && dto.getId() != 0) {
                idDocumento = dto.getId();
                break;
            } else if (arg instanceof com.tailoy.inv.dto.DespachoSucursalDTO dto && dto.getId() != 0) {
                idDocumento = dto.getId();
                break;
            }
        }

        if (idDocumento == null) {
            throw new IllegalArgumentException(
                    "No se pudo extraer el ID del documento para registrar movimiento de inventario.");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepo.findByCorreo(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        var method = ((org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature()).getMethod();
        Inventario[] anotaciones;

        if (method.isAnnotationPresent(Movimientos.class)) {
            anotaciones = method.getAnnotation(Movimientos.class).value();
        } else {
            anotaciones = new Inventario[] { method.getAnnotation(Inventario.class) };
        }

        for (Inventario inventario : anotaciones) {
            List<? extends Object> productos;

            switch (inventario.tipoInventario()) {
                case CANCELACION_OC, REGISTRO_OC, APROBACION_OC, RECHAZO_OC, ACTUALIZACION_OC ->
                    productos = ordenProductoRepo.findByOrdenCompraId(idDocumento);
                case CANCELACION_DS, REGISTRO_DS, APROBACION_DS, RECHAZO_DS, ACTUALIZACION_DS ->
                    productos = despachoProductoRepo.findByDespachoSucursalId(idDocumento);
                default -> throw new IllegalStateException("Tipo de movimiento no reconocido.");
            }

            for (Object obj : productos) {
                Producto producto;
                int cantidad;

                if (obj instanceof OrdenCompraProducto ocp) {
                    producto = ocp.getProducto();
                    cantidad = ocp.getCantidad();
                } else if (obj instanceof DespachoSucursalProducto dsp) {
                    producto = dsp.getProducto();
                    cantidad = dsp.getCantidad();
                } else {
                    continue;
                }

                MovimientoInventario movimiento = new MovimientoInventario();
                movimiento.setProducto(producto);
                movimiento.setCantidad(cantidad);
                movimiento.setFecha(LocalDate.now());
                movimiento.setUsuario(usuario);
                movimiento.setTipoMovimiento(inventario.tipoInventario().getTipoMovimiento());

                inventarioRepo.save(movimiento);
            }
        }

        return result;
    }

}
