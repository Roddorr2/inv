package com.tailoy.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.command.CambiarEstadoProveedorCommand;
import com.tailoy.inv.command.ModificarProveedorCommand;
import com.tailoy.inv.command.RegistrarProveedorCommand;
import com.tailoy.inv.dto.ProveedorDTO;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.repository.ProveedorRepository;
import com.tailoy.inv.service.ProveedorService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProveedorServiceImpl implements ProveedorService {
	@Autowired
	private ProveedorRepository repo;

	@Autowired
	private RegistrarProveedorCommand registrarProveedorCommand;

	@Autowired
	private ModificarProveedorCommand modificarProveedorCommand;

	@Autowired
	private CambiarEstadoProveedorCommand cambiarEstadoProveedorCommand;

	@Auditable(accion = "Registro de proveedores", tipo = TipoAccionEnum.REGISTRO, modulo = ModuloEnum.PROVEEDOR)
	@Override
	@Transactional
	public Proveedor registrarProveedor(ProveedorDTO proveedorDTO) {
		return registrarProveedorCommand.ejecutar(proveedorDTO, null);
	}

	@Override
	public List<Proveedor> listarProveedores() {
		return repo.findAll();
	}

	@Override
	public Proveedor obtenerPorId(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
	}

	@Override
	public Proveedor obtenerPorRuc(String ruc) {
		return repo.findByRuc(ruc)
				.orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con RUC: " + ruc));
	}

	@Auditable(accion = "Modificación de proveedores", tipo = TipoAccionEnum.MODIFICACION, modulo = ModuloEnum.PROVEEDOR)
	@Override
	@Transactional
	public Proveedor modificarProveedor(int idProveedor, ProveedorDTO proveedorDTO) {
		return modificarProveedorCommand.ejecutar(proveedorDTO, idProveedor);
	}

	@Auditable(accion = "Activación/desactivación de proveedores", tipo = TipoAccionEnum.CAMBIO_ESTADO, modulo = ModuloEnum.PROVEEDOR)
	@Override
	@Transactional
	public void cambiarEstadoProveedor(int idProveedor, boolean nuevoEstado) {
		ProveedorDTO dto = new ProveedorDTO();
		dto.setEstado(nuevoEstado);
		cambiarEstadoProveedorCommand.ejecutar(dto, idProveedor);
	}

	@Override
	public List<Proveedor> buscarPorNombreORucOTelefonoODireccion(String q) {
		return repo.findByNombreOrRucOrTelefonoOrDireccionContainingIgnoreCase(q);
	}

	@Override
	public boolean existeProveedoroPorRuc(String ruc) {
		return repo.existsByRuc(ruc.trim());
	}

	@Override
	public boolean existeProveedorPorNombre(String nombre) {
		return repo.existsByNombre(nombre.trim());
	}

}
