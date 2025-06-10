package com.tailoy.inv.audit;

public enum TipoAccionEnum {
    REGISTRO(1),
    MODIFICACION(2),
    CAMBIO_ESTADO(3),
    CONSULTA(4);

    private final int codigo;

    TipoAccionEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
