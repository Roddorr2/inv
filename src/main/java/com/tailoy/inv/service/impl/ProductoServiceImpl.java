package com.tailoy.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.dto.ProductoDTO;
import com.tailoy.inv.model.Producto;
import com.tailoy.inv.model.Subcategoria;
import com.tailoy.inv.repository.ProductoRepository;
import com.tailoy.inv.repository.SubcategoriaRepository;
import com.tailoy.inv.service.ProductoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoRepository repo;
	@Autowired
	private SubcategoriaRepository subcategoriaRepo;
	
	@Auditable(
		accion = "Registro de producto", 
		tipo = TipoAccionEnum.REGISTRO, 
		modulo = ModuloEnum.PRODUCTO
		)
	@Override
	@Transactional
	public Producto registrarProducto(ProductoDTO dto) {
		if (repo.existsByCodigo(dto.getCodigo())) {
			throw new IllegalArgumentException("Ya existe un producto con ese código");
		}
		Subcategoria subcategoria = subcategoriaRepo.findById(dto.getSubcategoria().getId())
				.orElseThrow(() -> new EntityNotFoundException("Subcategoría no encontrada"));
		
	Producto producto = new Producto();
	producto.setCodigo(dto.getCodigo());
	producto.setNombre(dto.getNombre());
	producto.setMarca(dto.getMarca());
	producto.setDescripcion(dto.getDescripcion());
	producto.setStock(dto.getStock());
	producto.setPrecioUnitario(dto.getPrecioUnitario());
	producto.setUnidadMedida(dto.getUnidadMedida());
	producto.setEstado(true);
	producto.setSubcategoria(subcategoria);
	
	return repo.save(producto);
	}
	
	@Auditable(
		accion = "Modificación de producto", 
		tipo = TipoAccionEnum.MODIFICACION, 
		modulo = ModuloEnum.PRODUCTO
		)
	@Override
	@Transactional
	public Producto modificarProducto(int id, ProductoDTO dto) {
		Producto producto = repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		if (producto.getCodigo() != dto.getCodigo() && repo.existsByCodigo(dto.getCodigo())) {
			throw new IllegalArgumentException("Ya existe otro producto con ese código");
		}
		
		producto.setCodigo(dto.getCodigo());
		producto.setNombre(dto.getNombre());
		producto.setMarca(dto.getMarca());
		producto.setDescripcion(dto.getDescripcion());
		producto.setStock(dto.getStock());
		producto.setPrecioUnitario(dto.getPrecioUnitario());
		producto.setUnidadMedida(dto.getUnidadMedida());
		
		if (dto.getSubcategoria().getId() != producto.getSubcategoria().getId()) {
			Subcategoria nsubcategoria = subcategoriaRepo.findById(dto.getSubcategoria().getId())
					.orElseThrow(() -> new EntityNotFoundException("Subcategoría no encontrada"));
			producto.setSubcategoria(nsubcategoria);
		}
		
		return repo.save(producto);
	}
	
	@Auditable(
		accion = "Activación/desactivación de producto", 
		tipo = TipoAccionEnum.CAMBIO_ESTADO, 
		modulo = ModuloEnum.PRODUCTO
		)
	@Override
	@Transactional
	public void cambiarEstadoProducto(int id, boolean nuevoEstado) {
		Producto producto = repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		if (producto.isEstado() == nuevoEstado) {
			throw new IllegalArgumentException("El producto ya tiene ese estado.");
		}
		
		producto.setEstado(nuevoEstado);
		repo.save(producto);
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
	        return repo.findByNombreContainingIgnoreCaseOrMarcaContainingIgnoreCaseOrCodigo(String.valueOf(codigo), q, codigo);
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
