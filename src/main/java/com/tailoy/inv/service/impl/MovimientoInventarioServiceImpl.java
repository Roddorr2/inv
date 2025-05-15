package com.tailoy.inv.service.impl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.model.MovimientoInventario;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.repository.MovimientoInventarioRepository;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.repository.UsuarioRepository;
import com.tailoy.inv.service.MovimientoInventarioService;

@Service
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository repo;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public void registrarAjusteStock(int productoId, int cantidad, String motivo, int tipoMovimiento, LocalDateTime fecha, int usuarioId) {
        Optional<Producto> producto = productoRepo.findById(productoId);
        Optional<Usuario> usuario = usuarioRepo.findById(usuarioId);

        if (producto.isEmpty() || usuario.isEmpty()) {
            throw new RuntimeException("Producto o usuario no encontrado.");
        }

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setCantidad(cantidad);
        movimiento.setNombre(motivo);
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setProducto(producto.get());
        movimiento.setUsuario(usuario.get());

        repo.save(movimiento);
    }

    @Override
    public List<MovimientoInventario> listarMovimientos() {
        return repo.findAll();
    }

    @Override 
    public List<MovimientoInventario> filtrarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return repo.findByFechasBetween(inicio, fin);
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
    public byte[] exportarMovimientos(List<MovimientoInventario> movimientos, String formato) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Movimientos");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("ID");
            header.createCell(2).setCellValue("ID");
            header.createCell(3).setCellValue("ID");
            header.createCell(4).setCellValue("ID");
            header.createCell(5).setCellValue("ID");
            header.createCell(6).setCellValue("ID");

            int rowNum = 1;
            for (MovimientoInventario mov : movimientos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(mov.getId());
                row.createCell(1).setCellValue(mov.getProducto().getNombre());
                row.createCell(2).setCellValue(mov.getCantidad());
                row.createCell(3).setCellValue(mov.getTipoMovimiento());
                row.createCell(4).setCellValue(mov.getNombre());
                row.createCell(5).setCellValue(mov.getUsuario().getNombre());
                row.createCell(6).setCellValue(mov.getFecha().toString());
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al exportar movimientos", e);
        }
    }

}
