package com.tailoy.inv.inventory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(Movimientos.class)
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Inventario {
    TipoMovimientoInventarioEnum tipoInventario();
}
