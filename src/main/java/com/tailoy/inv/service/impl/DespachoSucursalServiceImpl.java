package com.tailoy.inv.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.dto.DespachoSucursalDTO;
import com.tailoy.inv.model.DespachoSucursal;
import com.tailoy.inv.model.DespachoSucursalProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Sucursal;
import com.tailoy.inv.repository.DespachoSucursalProductoRepository;
import com.tailoy.inv.repository.DespachoSurcursalRepository;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.repository.SucursalRepository;
import com.tailoy.inv.service.DespachoSucursalService;

@Service
public class DespachoSucursalServiceImpl implements DespachoSucursalService {
    @Autowired
    private DespachoSurcursalRepository despachoSucursalRepo;
    @Autowired
    private DespachoSucursalProductoRepository despachoProductoRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;

    @Override
    @Transactional
    public DespachoSucursal registrarDespacho(DespachoSucursalDTO dto) {
        Sucursal sucursal = sucursalRepo.findById(dto.getSucursal().getId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        DespachoSucursal despacho = new DespachoSucursal();
        despacho.setSucursal(sucursal);
        despacho.setFecha(dto.getFechaDespacho());
        despacho.setEstadoOperacion(1);

        DespachoSucursal despachoS = despachoSucursalRepo.save(despacho);

        List<DespachoSucursalProducto> productos = dto.getProductos().stream().map(p -> {
            Producto producto = productoRepo.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DespachoSucursalProducto d = new DespachoSucursalProducto();
            d.setProducto(producto);
            d.setCantidad(p.getCantidad());
            d.setPrecioUnitario(p.getPrecioUnitario());
            d.setObservaciones(p.getObservaciones());
            d.setDespachoSucursal(despachoS);
            return d;
        }).collect(Collectors.toList());

        despachoProductoRepo.saveAll(productos);

        return despachoS;
    }

    @Override
    public List<DespachoSucursal> listarDespachos() {
        return despachoSucursalRepo.findAll();
    }

    @Override
    public DespachoSucursal obtenerPorId(int id) {
        return despachoSucursalRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado."));
    }

    @Override
    public List<DespachoSucursal> listarPorEstado(int estadoOperacion) {
        return despachoSucursalRepo.findByEstadoOperacion(estadoOperacion);
    }

    @Override
    public List<DespachoSucursal> buscarPorFechas(LocalDateTime desde, LocalDateTime hasta) {
        return despachoSucursalRepo.findByFechaBetween(desde, hasta);
    }

    @Override
    @Transactional
    public void actualizarEstado(int despachoId, int nuevoEstado) {
        DespachoSucursal despacho = despachoSucursalRepo.findById(despachoId)
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado"));
        despacho.setEstadoOperacion(nuevoEstado);
        despachoSucursalRepo.save(despacho);
    }

    @Override
    public DespachoSucursal obtenerDespachoPorId(int despachoId, int estadoOperacion) {
        return despachoSucursalRepo.findByIdAndEstadoOperacion(despachoId, estadoOperacion)
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado con el estado indicado."));
    }

    @Override
    public byte[] exportarDespacho(int despachoId, String formato) {
        DespachoSucursal despacho = despachoSucursalRepo.findById(despachoId)
                .orElseThrow(() -> new IllegalArgumentException("Despacho no encontrado"));

        List<DespachoSucursalProducto> productos = despachoProductoRepo
                .findByDespachoSucursalId(despachoId);

        try (Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Despacho");

            Row header1 = sheet.createRow(0);
            header1.createCell(0).setCellValue("Despacho ID");
            header1.createCell(1).setCellValue(String.valueOf(despacho.getId()));

            Row header2 = sheet.createRow(1);
            header2.createCell(0).setCellValue("Fecha");
            header2.createCell(1).setCellValue(despacho.getFecha().toString());

            Row header3 = sheet.createRow(2);
            header3.createCell(0).setCellValue("Sucursal");
            header3.createCell(1).setCellValue(despacho.getSucursal().getCiudad());

            // Cabecera de productos
            Row header = sheet.createRow(5);
            header.createCell(0).setCellValue("Producto ID");
            header.createCell(1).setCellValue("Nombre");
            header.createCell(2).setCellValue("Cantidad");
            header.createCell(3).setCellValue("Precio Unitario");
            header.createCell(4).setCellValue("Observaciones");

            // Datos de productos
            int rowIdx = 6;
            for (DespachoSucursalProducto producto : productos) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(String.valueOf(producto.getProducto().getId()));
                row.createCell(1).setCellValue(producto.getProducto().getNombre());
                row.createCell(2).setCellValue((double) producto.getCantidad());
                row.createCell(3).setCellValue(producto.getPrecioUnitario());
                row.createCell(4).setCellValue(producto.getObservaciones());
            }

            workbook.write(out);
            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error al exportar a Excel:" + e);
        }
    }
}
