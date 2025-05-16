package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.dto.ProductoOrdenDTO;
import com.tailoy.inv.model.OrdenCompra;
import com.tailoy.inv.model.OrdenCompraProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.repository.OrdenCompraProductoRepository;
import com.tailoy.inv.repository.OrdenCompraRepository;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.service.OrdenCompraProductoService;

import jakarta.transaction.Transactional;

@Service
public class OrdenCompraProductoServiceImpl implements OrdenCompraProductoService{
    @Autowired
    private OrdenCompraProductoRepository repo;
    @Autowired
    private OrdenCompraRepository ordenCompraRepo;
    @Autowired
    private ProductoRepository productoRepo;

    @Override
    @Transactional
    public void registrarProductosOrden(List<ProductoOrdenDTO> productosOrden, int ordenCompraId) {
        if (productosOrden == null || productosOrden.isEmpty()) {
            throw new IllegalArgumentException("La lista de productos de la orden de compra no puede estar vacía.");
        }

        if (ordenCompraId <= 0) {
            throw new IllegalArgumentException("ID de orden de compra no válido.");
        }

        OrdenCompra orden = ordenCompraRepo.findById(ordenCompraId)
                .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada."));

        List<OrdenCompraProducto> productos = productosOrden.stream().map(dto -> {
            Producto producto = productoRepo.findById(dto.getProducto().getId() )
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado."));

            OrdenCompraProducto ocp = new OrdenCompraProducto();
            ocp.setOrdenCompra(orden);
            ocp.setProducto(producto);
            ocp.setCantidad(dto.getCantidad());
            ocp.setPrecioUnitario(dto.getPrecioUnitario());
            ocp.setObservaciones(dto.getObservaciones());

            return ocp;
        }).collect(Collectors.toList());

        repo.saveAll(productos);
    }

    @Override
    public List<OrdenCompraProducto> obtenerProductosPorOrden(int ordenCompraId) {
        if (ordenCompraId <= 0) {
            throw new IllegalArgumentException("ID de orden de compra no válido.");
        }
        return repo.findByOrdenCompraId(ordenCompraId);
    }

    @Override
    public void eliminarProductosOrden(int ordenCompraId) {
        if (ordenCompraId <= 0) {
            throw new IllegalArgumentException("ID de orden de compra no válido.");
        }
        throw new UnsupportedOperationException("Unimplemented method 'eliminarProductosOrden'");
    }

    @Override
    public boolean validarStockParaOrden(List<ProductoOrdenDTO> productosOrden) {
        for (ProductoOrdenDTO dto : productosOrden) {
            Producto producto = productoRepo.findById(dto.getProducto().getId())
                    .orElse(null);
            if (producto == null || producto.getStock() < dto.getCantidad()) {
                return false;
            }
        }
        return true;
    }

}
