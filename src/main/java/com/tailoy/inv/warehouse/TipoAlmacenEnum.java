package com.tailoy.inv.warehouse;

public enum TipoAlmacenEnum {
    SALIDA(0),
    ENTRADA(1);

    private final int tipo;

    TipoAlmacenEnum(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }
}
