package com.tailoy.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.command.CambiarEstadoProductoCommand;
import com.tailoy.inv.command.ModificarProductoCommand;
import com.tailoy.inv.command.RegistrarProductoCommand;
import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.service.ProductoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private RegistrarProductoCommand registrarProductoCommand;

	@Autowired
	private ModificarProductoCommand modificarProductoCommand;

	@Autowired
	private CambiarEstadoProductoCommand cambiarEstadoProductoCommand;

	@Autowired
	private ProductoRepository repo;

	@Auditable(accion = "Registro de producto", tipo = TipoAccionEnum.REGISTRO, modulo = ModuloEnum.PRODUCTO)
	@Override
	@Transactional
	public Producto registrarProducto(ProductoDTO dto) {
		return registrarProductoCommand.ejecutar(dto, null);
	}

	@Auditable(accion = "Modificación de producto", tipo = TipoAccionEnum.MODIFICACION, modulo = ModuloEnum.PRODUCTO)
	@Override
	@Transactional
	public Producto modificarProducto(int id, ProductoDTO dto) {
		return modificarProductoCommand.ejecutar(dto, id);
	}

	@Auditable(accion = "Activación/desactivación de producto", tipo = TipoAccionEnum.CAMBIO_ESTADO, modulo = ModuloEnum.PRODUCTO)
	@Override
	@Transactional
	public void cambiarEstadoProducto(int id, boolean nuevoEstado) {
		ProductoDTO dto = new ProductoDTO();
		dto.setEstado(nuevoEstado);
		cambiarEstadoProductoCommand.ejecutar(dto, id);
	}

	@Override
	public Producto obtenerPorId(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
	}

	@Override
	public List<Producto> buscarPorNombreOMarcaOCodigo(String q) {
		try {
			int codigo = Integer.parseInt(q);
			return repo.findByNombreContainingIgnoreCaseOrMarcaContainingIgnoreCaseOrCodigo(String.valueOf(codigo), q,
					codigo);
		} catch (NumberFormatException e) {
			return repo.findByNombreOrMarcaContainingIgnoreCase(q);
		}
	}

	@Override
	public List<Producto> listarProductos() {
		return repo.findAll();
	}

	@Override
	public List<Producto> listarActivos() {
		return repo.findByEstadoTrue();
	}

	@Override
	public boolean existeCodigoProducto(int codigo) {
		return repo.existsByCodigo(codigo);
	}

}
