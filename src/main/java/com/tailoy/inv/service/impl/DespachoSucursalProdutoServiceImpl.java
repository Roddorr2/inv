package com.tailoy.inv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.dto.ProductoDespachoDTO;
import com.tailoy.inv.model.DespachoSucursal;
import com.tailoy.inv.model.DespachoSucursalProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.repository.DespachoSucursalProductoRepository;
import com.tailoy.inv.repository.DespachoSurcursalRepository;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.service.DespachoSucursalProductoService;

@Service
public class DespachoSucursalProdutoServiceImpl implements DespachoSucursalProductoService {

    @Autowired
    private DespachoSucursalProductoRepository despachoSucursalProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DespachoSurcursalRepository despachoSucursalRepository;

    @Override
    public void registrarProductosDespacho(List<ProductoDespachoDTO> productosDespacho, int despachoId) {
        if (productosDespacho == null || productosDespacho.isEmpty()) {
            throw new IllegalArgumentException("La lista de productos de despacho no puede estar vacía.");
        }

        if (despachoId  <= 0) {
            throw new IllegalArgumentException("ID de despacho no válido.");
        }

        DespachoSucursal despacho = despachoSucursalRepository.findById(despachoId)
                .orElseThrow(() -> new IllegalArgumentException("Despacho no encontrado."));

        if (!validarStockParaDespacho(productosDespacho)) {
            throw new IllegalArgumentException("No hay suficiente stock para uno o más productos.");
        }

        List<DespachoSucursalProducto> entidades = new ArrayList<>();

        for (ProductoDespachoDTO dto : productosDespacho) {
            if (dto.getProductoId() <=0 || dto.getCantidad() <= 0) {
                throw new IllegalArgumentException("Datos inválidos para el ID y la cantidad del producto.");
            }

            Producto producto = productoRepository.findById(dto.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
            
            DespachoSucursalProducto dsp = new DespachoSucursalProducto();
            dsp.setProducto(producto);
            dsp.setCantidad(dto.getCantidad());
            dsp.setPrecioUnitario(producto.getPrecioUnitario());
            dsp.setObservaciones(dto.getObservaciones());
            dsp.setDespachoSucursal(despacho);

            entidades.add(dsp);
        }

        despachoSucursalProductoRepository.saveAll(entidades);
    }

    @Override
    public List<DespachoSucursalProducto> obtenerProductosPorDespacho(int despachoId) {
        if (despachoId <= 0) {
            throw new IllegalArgumentException("ID de despacho no válido.");
        }
        return despachoSucursalProductoRepository.findByDespachoSucursalId(despachoId);
    }

    @Override
    public void eliminarProductoDespacho(int despachoId) {
        if (despachoId <= 0) {
            throw new IllegalArgumentException("ID de despacho no válido.");
        }
        despachoSucursalProductoRepository.deleteByDespachoSucursalId(despachoId);
    }

    @Override
    public boolean validarStockParaDespacho(List<ProductoDespachoDTO> productosDespacho) {
        for (ProductoDespachoDTO dto : productosDespacho) {
            Producto producto = productoRepository.findById(dto.getProductoId())
                    .orElse(null);
            if (producto == null || producto.getStock() < dto.getCantidad()) {
                return false;
            }
        }
        return true;
    }
    
}
