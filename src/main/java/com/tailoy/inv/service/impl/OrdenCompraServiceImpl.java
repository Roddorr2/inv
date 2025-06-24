package com.tailoy.inv.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.dto.OrdenCompraDTO;
import com.tailoy.inv.dto.OrdenCompraDetalladoDTO;
import com.tailoy.inv.dto.ProductoOrdenDTO;
import com.tailoy.inv.model.OrdenCompra;
import com.tailoy.inv.model.OrdenCompraProducto;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.repository.OrdenCompraProductoRepository;
import com.tailoy.inv.repository.OrdenCompraRepository;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.repository.ProveedorRepository;
import com.tailoy.inv.service.OrdenCompraService;

import jakarta.transaction.Transactional;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {
	@Autowired
	private OrdenCompraRepository repo;
	@Autowired
	private OrdenCompraProductoRepository ordenProductoRepo;
	@Autowired
	private ProductoRepository productoRepo;
	@Autowired
	private ProveedorRepository proveedorRepo;
	
	@Override
	@Transactional
	public OrdenCompra registrarOrdenCompra(OrdenCompraDTO dto) {
		Proveedor proveedor = proveedorRepo.findById(dto.getProveedor().getId())
				.orElseThrow(() -> new RuntimeException("Proveedor no encontrado."));
		
		OrdenCompra orden = new OrdenCompra();
		orden.setProveedor(proveedor);
		orden.setFecha(dto.getFecha());
		orden.setEstadoOperacion(1);
		
		OrdenCompra ordenS = repo.save(orden);
		
		List<OrdenCompraProducto> productos = dto.getProductos().stream().map(p -> {
			Producto producto = productoRepo.findById(p.getId())
					.orElseThrow(() -> new RuntimeException("Producto no encontrado."));
			
			OrdenCompraProducto op = new OrdenCompraProducto();
			op.setProducto(producto);
			op.setCantidad(p.getCantidad());
			op.setPrecioUnitario(p.getPrecioUnitario());
			op.setObservaciones(p.getObservaciones());
			op.setOrdenCompra(ordenS);
			return op;
		}).collect(Collectors.toList());
		
		ordenProductoRepo.saveAll(productos);
		
		return ordenS;
	}
	
	@Override
	@Transactional
	public OrdenCompra actualizarOrdenCompra(OrdenCompraDTO dto) {
		OrdenCompra orden = repo.findById(dto.getId())
				.orElseThrow(() -> new RuntimeException("Orden no encontrada."));

		Proveedor proveedor = proveedorRepo.findById(dto.getProveedor().getId())
				.orElseThrow(() -> new RuntimeException("Proveedor no encontrado."));

		orden.setProveedor(proveedor);
		orden.setFecha(dto.getFecha());
		orden.setEstadoOperacion(dto.getEstadoOperacion());
		repo.save(orden);

		List<OrdenCompraProducto> productosActuales = ordenProductoRepo.findByOrdenCompraId(orden.getId());
		Map<Integer, OrdenCompraProducto> mapActuales = productosActuales.stream()
				.collect(Collectors.toMap(OrdenCompraProducto::getId, p -> p));

		List<OrdenCompraProducto> productosActualizados = new ArrayList<>();

		for (ProductoOrdenDTO prodDto : dto.getProductos()) {
			Producto producto = productoRepo.findById(prodDto.getId())
					.orElseThrow(() -> new RuntimeException("Producto no encontrado."));

			OrdenCompraProducto productoActual;
			if (prodDto.getId() != 0 && mapActuales.containsKey(prodDto.getId())) {
				productoActual = mapActuales.get(prodDto.getId());
				productoActual.setCantidad(prodDto.getCantidad());
				productoActual.setPrecioUnitario(prodDto.getPrecioUnitario());
				productoActual.setObservaciones(prodDto.getObservaciones());
				productoActual.setProducto(producto);
				mapActuales.remove(prodDto.getId());
			} else {
				productoActual = new OrdenCompraProducto();
				productoActual.setOrdenCompra(orden);
				productoActual.setProducto(producto);
				productoActual.setCantidad(prodDto.getCantidad());
				productoActual.setPrecioUnitario(prodDto.getPrecioUnitario());
				productoActual.setObservaciones(prodDto.getObservaciones());
			}

			productosActualizados.add(productoActual);
		}

		for (OrdenCompraProducto eliminado : mapActuales.values()) {
			ordenProductoRepo.delete(eliminado);
		}

		ordenProductoRepo.saveAll(productosActualizados);

		return orden;
	}

	@Override
	public List<OrdenCompraDetalladoDTO> listarOrdenes() {
		List<Object[]> results = repo.findOrdenesCompraDetalle();

		return results.stream()
			.map(row -> new OrdenCompraDetalladoDTO(
				(String) row[0],
				((java.sql.Timestamp) row[1]).toLocalDateTime().toLocalDate(),
				(String) row[2],
				(String) row[3],
				(double) row[4],
				(int) row[5],
				(double) row[6],
				(String) row[7]
			))
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
	public List<OrdenCompra> buscarPorFechas(LocalDateTime desde, LocalDateTime hasta) {
		return repo.findByFechaBetween(desde, hasta);
	}
	
	@Override
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
