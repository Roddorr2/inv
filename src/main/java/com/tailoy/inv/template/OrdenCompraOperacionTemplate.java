package com.tailoy.inv.template;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.dto.OrdenCompraDTO;
import com.tailoy.inv.model.OrdenCompra;
import com.tailoy.inv.repository.OrdenCompraProductoRepository;
import com.tailoy.inv.repository.OrdenCompraRepository;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.repository.ProveedorRepository;

public abstract class OrdenCompraOperacionTemplate {

    @Autowired
    protected OrdenCompraRepository ordenCompraRepo;
    @Autowired
    protected OrdenCompraProductoRepository ordenProductoRepo;
    @Autowired
    protected ProductoRepository productoRepo;
    @Autowired
    protected ProveedorRepository proveedorRepo;

    public OrdenCompra ejecutar(OrdenCompraDTO dto) {
        validar(dto);
        OrdenCompra orden = procesarOrden(dto);
        return orden;
    }

    protected abstract void validar(OrdenCompraDTO dto);

    protected abstract OrdenCompra procesarOrden(OrdenCompraDTO dto);

}
