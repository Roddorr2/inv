package com.tailoy.inv.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.command.ActualizarOrdenCompraCommand;
import com.tailoy.inv.command.RegistrarOrdenCompraCommand;
import com.tailoy.inv.dto.OrdenCompraDTO;
import com.tailoy.inv.dto.OrdenCompraDetalladoDTO;
import com.tailoy.inv.inventory.Inventario;
import com.tailoy.inv.inventory.TipoMovimientoInventarioEnum;
import com.tailoy.inv.model.OrdenCompra;
import com.tailoy.inv.model.OrdenCompraProducto;
import com.tailoy.inv.repository.OrdenCompraProductoRepository;
import com.tailoy.inv.repository.OrdenCompraRepository;
import com.tailoy.inv.service.OrdenCompraService;
import com.tailoy.inv.warehouse.Almacen;
import com.tailoy.inv.warehouse.TipoAlmacenEnum;

import jakarta.transaction.Transactional;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {
	@Autowired
	private OrdenCompraRepository repo;
	@Autowired
	private OrdenCompraProductoRepository ordenProductoRepo;
	@Autowired
	private RegistrarOrdenCompraCommand registrarOrdenCompraCommand;
	@Autowired
	private ActualizarOrdenCompraCommand actualizarOrdenCompraCommand;

	@Override
	@Inventario(tipoInventario = TipoMovimientoInventarioEnum.REGISTRO_OC)
	@Transactional
	public OrdenCompra registrarOrdenCompra(OrdenCompraDTO dto) {
		return registrarOrdenCompraCommand.ejecutar(dto);
	}

	@Override
	@Inventario(tipoInventario = TipoMovimientoInventarioEnum.ACTUALIZACION_OC)
	@Transactional
	public OrdenCompra actualizarOrdenCompra(OrdenCompraDTO dto) {
		return actualizarOrdenCompraCommand.ejecutar(dto);
	}

	@Override
	public List<OrdenCompraDetalladoDTO> listarOrdenes() {
		List<Object[]> results = repo.findOrdenesCompraDetalle();

		return results.stream()
				.map(row -> new OrdenCompraDetalladoDTO(
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
	public OrdenCompra obtenerPorId(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new RuntimeException("Orden de compra no encontrada"));
	}

	@Override
	public List<OrdenCompra> listarPorEstado(int estadoOperacion) {
		return repo.findByEstadoOperacion(estadoOperacion);
	}

	@Override
	public List<OrdenCompra> buscarPorFechas(LocalDate desde, LocalDate hasta) {
		return repo.findByFechaBetween(desde, hasta);
	}

	@Override
	@Almacen(tipoAlmacen = TipoAlmacenEnum.ENTRADA)
	@Inventario(tipoInventario = TipoMovimientoInventarioEnum.CANCELACION_OC)
	@Inventario(tipoInventario = TipoMovimientoInventarioEnum.APROBACION_OC)
	@Inventario(tipoInventario = TipoMovimientoInventarioEnum.RECHAZO_OC)
	@Transactional
	public void actualizarEstado(int ordenId, int nuevoEstado) {
		OrdenCompra orden = repo.findById(ordenId)
				.orElseThrow(() -> new RuntimeException("Orden no encontrada."));
		orden.setEstadoOperacion(nuevoEstado);
		repo.save(orden);
	}

	@Override
	public byte[] exportarOrden(int ordenId) {
		OrdenCompra orden = repo.findById(ordenId)
				.orElseThrow(() -> new IllegalArgumentException("Orden de compra no encontrada."));

		List<OrdenCompraProducto> productos = ordenProductoRepo.findByOrdenCompraId(ordenId);

		try (Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream()) {

			Sheet sheet = workbook.createSheet("Orden de Compra");

			Row header1 = sheet.createRow(0);
			header1.createCell(0).setCellValue("Orden ID");
			header1.createCell(1).setCellValue(String.valueOf(orden.getId()));

			Row header2 = sheet.createRow(1);
			header2.createCell(0).setCellValue("Fecha");
			header2.createCell(1).setCellValue(orden.getFecha().toString());

			Row header3 = sheet.createRow(2);
			header3.createCell(0).setCellValue("Proveedor");
			header3.createCell(1).setCellValue(orden.getProveedor().getNombre());

			Row header = sheet.createRow(5);
			header.createCell(0).setCellValue("Producto ID");
			header.createCell(1).setCellValue("Nombre");
			header.createCell(2).setCellValue("Cantidad");
			header.createCell(3).setCellValue("Precio Unitario");
			header.createCell(4).setCellValue("Observaciones");

			int rowIdx = 6;
			for (OrdenCompraProducto producto : productos) {
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
			throw new RuntimeException("Error al exportar la orden a Excel:" + e);
		}
	}
}
