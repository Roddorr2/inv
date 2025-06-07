package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.dto.SucursalDTO;
import com.tailoy.inv.model.Sucursal;
import com.tailoy.inv.repository.SucursalRepository;
import com.tailoy.inv.service.SucursalService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SucursalServiceImpl implements SucursalService {
	@Autowired
	private SucursalRepository repo;
	
	@Override
    public SucursalDTO registrarSucursal(SucursalDTO sucursalDTO) {
        if (repo.existsByCorreo(sucursalDTO.getCorreo())) {
            throw new IllegalArgumentException("Ya existe una sucursal con ese correo.");
        }

        Sucursal sucursal = new Sucursal();
        sucursal.setCiudad(sucursalDTO.getCiudad());
        sucursal.setDireccion(sucursalDTO.getDireccion());
        sucursal.setCorreo(sucursalDTO.getCorreo());

        Sucursal guardada = repo.save(sucursal);
        return new SucursalDTO(guardada);
    }

    @Override
    public List<SucursalDTO> listarSucursales() {
        return repo.findAll()
                .stream()
                .map(SucursalDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public SucursalDTO obtenerSucursalPorId(int id) {
        Sucursal sucursal = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con ID: " + id));
        return new SucursalDTO(sucursal);
    }

    @Override
    public List<SucursalDTO> obtenerSucursalPorCorreo(String correo) {
    	List<SucursalDTO> sucursales = repo.findByCorreo(correo);
        return sucursales;
    }

    @Override
    public SucursalDTO actualizarSucursal(int id, SucursalDTO dto) {
        Sucursal sucursal = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con ID: " + id));

        if (!sucursal.getCorreo().equalsIgnoreCase(dto.getCorreo())
                && repo.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("Ya existe otra sucursal con ese correo.");
        }

        sucursal.setCorreo(dto.getCorreo());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setCiudad(dto.getCiudad());

        return new SucursalDTO(repo.save(sucursal));
    }

    @Override
    public List<SucursalDTO> buscarPorDireccionOCorreo(String q) {
        List<SucursalDTO> sucursales = repo.findByDireccionContainingIgnoreCaseOrCorreoContainingIgnoreCase(q.trim(), q.trim());
        return sucursales;
    }
    
    @Override
    public boolean existeSucursalPorCorreo(String correo) {
    	 return repo.existsByCorreo(correo.trim());
    }
	
}
