package com.tailoy.inv.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.command.ActualizarDespachoSucursalCommand;
import com.tailoy.inv.command.RegistrarDespachoSucursalCommand;
import com.tailoy.inv.dto.DespachoSucursalDTO;
import com.tailoy.inv.dto.DespachoSucursalDetalladoDTO;
import com.tailoy.inv.inventory.Inventario;
import com.tailoy.inv.inventory.TipoMovimientoInventarioEnum;
import com.tailoy.inv.model.DespachoSucursal;
import com.tailoy.inv.model.DespachoSucursalProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.repository.DespachoSucursalProductoRepository;
import com.tailoy.inv.repository.DespachoSurcursalRepository;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.service.DespachoSucursalService;
import com.tailoy.inv.warehouse.Almacen;
import com.tailoy.inv.warehouse.TipoAlmacenEnum;

@Service
public class DespachoSucursalServiceImpl implements DespachoSucursalService {
    @Autowired
    private DespachoSurcursalRepository despachoSucursalRepo;
    @Autowired
    private DespachoSucursalProductoRepository despachoProductoRepo;
    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private RegistrarDespachoSucursalCommand registrarDespachoCommand;

    @Autowired
    private ActualizarDespachoSucursalCommand actualizarDespachoCommand;

    @Override
    @Inventario(tipoInventario = TipoMovimientoInventarioEnum.REGISTRO_DS)
    @Transactional
    public DespachoSucursal registrarDespacho(DespachoSucursalDTO dto) {
        return registrarDespachoCommand.ejecutar(dto);
    }

    @Override
    public List<DespachoSucursalDetalladoDTO> listarDespachos() {
        List<Object[]> results = despachoSucursalRepo.findDespachoSucursalDetalle();

        return results.stream()
                .map(row -> new DespachoSucursalDetalladoDTO(
                        (int) row[0],
                        (String) row[1],
                        ((java.sql.Date) row[2]).toLocalDate(),
                        (String) row[3],
                        (String) row[4],
                        (double) row[5],
                        (int) row[6],
                        (double) row[7],
                        (String) row[8]))
                .collect(Collectors.toList());
    }

    @Override
    public DespachoSucursal obtenerPorId(int id) {
        return despachoSucursalRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado."));
    }

    @Override
    @Inventario(tipoInventario = TipoMovimientoInventarioEnum.ACTUALIZACION_DS)
    @Transactional
    public DespachoSucursal actualizarDespacho(DespachoSucursalDTO dto) {
        return actualizarDespachoCommand.ejecutar(dto);
    }

    @Override
    public List<DespachoSucursal> listarPorEstado(int estadoOperacion) {
        return despachoSucursalRepo.findByEstadoOperacion(estadoOperacion);
    }

    @Override
    public List<DespachoSucursal> buscarPorFechas(LocalDate desde, LocalDate hasta) {
        return despachoSucursalRepo.findByFechaBetween(desde, hasta);
    }

    @Override
    @Almacen(tipoAlmacen = TipoAlmacenEnum.SALIDA)
    @Inventario(tipoInventario = TipoMovimientoInventarioEnum.CANCELACION_DS)
    @Inventario(tipoInventario = TipoMovimientoInventarioEnum.APROBACION_OC)
    @Inventario(tipoInventario = TipoMovimientoInventarioEnum.RECHAZO_DS)
    @Transactional
    public void actualizarEstado(int despachoId, int nuevoEstado) {
        DespachoSucursal despacho = despachoSucursalRepo.findById(despachoId)
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado"));

        despacho.setEstadoOperacion(nuevoEstado);
        despachoSucursalRepo.save(despacho);

        if (nuevoEstado == 2) {
            List<DespachoSucursalProducto> productos = despachoProductoRepo.findByDespachoSucursalId(despachoId);

            for (DespachoSucursalProducto dp : productos) {
                Producto producto = dp.getProducto();
                int nuevoStock = producto.getStock() - dp.getCantidad();

                if (nuevoStock < 0) {
                    throw new RuntimeException("No hay suficiente stock para el despacho");
                }

                producto.setStock(nuevoStock);
                productoRepo.save(producto);
            }
        }
    }

    @Override
    public byte[] exportarDespacho(int despachoId) {
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
            header3.createCell(1).setCellValue(despacho.getSucursal().getCorreo());

            Row header = sheet.createRow(5);
            header.createCell(0).setCellValue("Producto ID");
            header.createCell(1).setCellValue("Nombre");
            header.createCell(2).setCellValue("Cantidad");
            header.createCell(3).setCellValue("Precio Unitario");
            header.createCell(4).setCellValue("Observaciones");

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
