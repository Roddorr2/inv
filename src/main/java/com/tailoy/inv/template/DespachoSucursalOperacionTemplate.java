package com.tailoy.inv.template;

import org.springframework.beans.factory.annotation.Autowired;

import com.tailoy.inv.dto.DespachoSucursalDTO;
import com.tailoy.inv.model.DespachoSucursal;
import com.tailoy.inv.repository.DespachoSucursalProductoRepository;
import com.tailoy.inv.repository.DespachoSurcursalRepository;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.repository.SucursalRepository;

public abstract class DespachoSucursalOperacionTemplate {

    @Autowired
    protected DespachoSurcursalRepository despachoSucursalRepo;

    @Autowired
    protected DespachoSucursalProductoRepository despachoProductoRepo;

    @Autowired
    protected ProductoRepository productoRepo;

    @Autowired
    protected SucursalRepository sucursalRepo;

    public DespachoSucursal ejecutar(DespachoSucursalDTO dto) {
        validar(dto);
        return procesarDespacho(dto);
    }

    protected abstract void validar(DespachoSucursalDTO dto);

    protected abstract DespachoSucursal procesarDespacho(DespachoSucursalDTO dto);
}
