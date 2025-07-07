package com.tailoy.inv.inventory;

public enum TipoMovimientoInventarioEnum {
    CANCELACION_OC(0),
    REGISTRO_OC(1),
    APROBACION_OC(2),
    RECHAZO_OC(3),
    ACTUALIZACION_OC(4),

    CANCELACION_DS(5),
    REGISTRO_DS(6),
    APROBACION_DS(7),
    RECHAZO_DS(8),
    ACTUALIZACION_DS(9);

    private final int tipoMovimiento;

    TipoMovimientoInventarioEnum(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

}
