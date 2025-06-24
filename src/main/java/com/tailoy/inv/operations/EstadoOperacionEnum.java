package com.tailoy.inv.operations;

public enum EstadoOperacionEnum {
    CANCELADO(0),
    PENDIENTE(1),
    APROBADO(2),
    RECHAZADO(3);

    private final int estado;

    EstadoOperacionEnum(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }
}
