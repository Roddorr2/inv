package com.tailoy.inv.audit;

public enum ModuloEnum {
    PRODUCTO(1),
    USUARIO(2),
    PROVEEDOR(3),
    SUCURSAL(4),
    CARGO(5),
    CATEGORIA(6),
    SUBCATEGORIA(7),
    HISTORIAL(8);

    private final int codigo;

    ModuloEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
