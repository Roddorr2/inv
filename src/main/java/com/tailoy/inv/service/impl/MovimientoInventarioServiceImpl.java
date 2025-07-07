package com.tailoy.inv.service.impl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.dto.MovimientoInventarioDetalleDTO;
import com.tailoy.inv.model.MovimientoInventario;
import com.tailoy.inv.repository.MovimientoInventarioRepository;
import com.tailoy.inv.service.MovimientoInventarioService;

@Service
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository repo;

    @Override
    public List<MovimientoInventarioDetalleDTO> listarMovimientos() {
        List<Object[]> resultados = repo.findMovimientos();

        return resultados.stream()
                .map(obj -> new MovimientoInventarioDetalleDTO(
                        (int) obj[0],
                        (int) obj[1],
                        (String) obj[2],
                        ((java.sql.Date) obj[3]).toLocalDate(),
                        (String) obj[4],
                        (String) obj[5]))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoInventario> filtrarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return repo.findByFechaBetween(inicio, fin);
    }

    @Override
    public List<MovimientoInventario> filtrarPorUsuario(int usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    @Override
    public List<MovimientoInventario> filtrarPorProducto(int productoId) {
        return repo.findByProductoId(productoId);
    }

    @Override
    public MovimientoInventario obtenerDetalleMovimiento(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimiento no encontrado"));
    }

    @Override
    public byte[] exportarMovimientos(int idMovimiento) {
        MovimientoInventario mov = repo.findById(idMovimiento)
                .orElseThrow(() -> new IllegalArgumentException("Movimiento no encontrado"));

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Movimientos");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Producto");
            header.createCell(2).setCellValue("Cantidad");
            header.createCell(3).setCellValue("Tipo Movimiento");
            header.createCell(4).setCellValue("Usuario");
            header.createCell(5).setCellValue("Fecha");

            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue(String.valueOf(mov.getId()));
            row.createCell(1).setCellValue(mov.getProducto().getNombre());
            row.createCell(2).setCellValue((double) mov.getCantidad());
            row.createCell(3).setCellValue(String.valueOf(mov.getTipoMovimiento()));
            row.createCell(4).setCellValue(mov.getUsuario().getNombre());
            row.createCell(5).setCellValue(mov.getFecha().toString());

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al exportar movimiento", e);
        }
    }

}
