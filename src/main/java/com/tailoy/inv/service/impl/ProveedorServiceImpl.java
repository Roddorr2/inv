package com.tailoy.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.dto.ProveedorDTO;
import com.tailoy.inv.model.Proveedor;
import com.tailoy.inv.repository.ProveedorRepository;
import com.tailoy.inv.service.ProveedorService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProveedorServiceImpl implements ProveedorService {
	@Autowired
    private ProveedorRepository repo;

	@Override
    @Transactional
    public Proveedor registrarProveedor(ProveedorDTO proveedorDTO) {
        if (repo.existsByRuc(proveedorDTO.getRuc())) {
            throw new IllegalArgumentException("Ya existe un proveedor con ese RUC.");
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setRuc(proveedorDTO.getRuc());
        proveedor.setTelefono(proveedorDTO.getTelefono());
        proveedor.setDireccion(proveedorDTO.getDireccion());
        proveedor.setEstado(true); 

        return repo.save(proveedor);
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

	    @Override
	    @Transactional
	    public Proveedor modificarProveedor(int idProveedor, ProveedorDTO proveedorDTO) {
	        Proveedor proveedor = repo.findById(idProveedor)
	                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + idProveedor));

	        if (!proveedor.getRuc().equals(proveedorDTO.getRuc()) &&
	            repo.existsByRuc(proveedorDTO.getRuc())) {
	            throw new IllegalArgumentException("Ya existe otro proveedor con ese RUC.");
	        }

	        proveedor.setNombre(proveedorDTO.getNombre());
	        proveedor.setRuc(proveedorDTO.getRuc());
	        proveedor.setTelefono(proveedorDTO.getTelefono());
	        proveedor.setDireccion(proveedorDTO.getDireccion());

	        return repo.save(proveedor);
	    }

	    @Override
	    @Transactional
	    public void cambiarEstadoProveedor(int idProveedor, boolean nuevoEstado) {
	        Proveedor proveedor = repo.findById(idProveedor)
	                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + idProveedor));

	        if (proveedor.isEstado() == nuevoEstado) {
	            throw new IllegalStateException("El proveedor ya tiene ese estado.");
	        }

	        proveedor.setEstado(nuevoEstado);
	        repo.save(proveedor);
	    }
}
