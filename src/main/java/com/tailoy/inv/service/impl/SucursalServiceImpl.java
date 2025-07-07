package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.command.ActualizarSucursalCommand;
import com.tailoy.inv.command.RegistrarSucursalCommand;
import com.tailoy.inv.dto.SucursalDTO;
import com.tailoy.inv.model.Sucursal;
import com.tailoy.inv.repository.SucursalRepository;
import com.tailoy.inv.service.SucursalService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SucursalServiceImpl implements SucursalService {
    @Autowired
    private SucursalRepository repo;

    @Autowired
    private ActualizarSucursalCommand actualizarSucursalCommand;

    @Autowired
    private RegistrarSucursalCommand registrarSucursalCommand;

    @Auditable(accion = "Registro de sucursales", tipo = TipoAccionEnum.REGISTRO, modulo = ModuloEnum.SUCURSAL)
    @Override
    public SucursalDTO registrarSucursal(SucursalDTO sucursalDTO) {
        return registrarSucursalCommand.ejecutar(sucursalDTO, null);
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

    @Auditable(accion = "Modificación de sucursales", tipo = TipoAccionEnum.MODIFICACION, modulo = ModuloEnum.SUCURSAL)
    @Override
    public SucursalDTO actualizarSucursal(int id, SucursalDTO dto) {
        return actualizarSucursalCommand.ejecutar(dto, id);
    }

    @Override
    public List<SucursalDTO> buscarPorDireccionOCorreo(String q) {
        List<SucursalDTO> sucursales = repo.findByDireccionContainingIgnoreCaseOrCorreoContainingIgnoreCase(q.trim(),
                q.trim());
        return sucursales;
    }

    @Override
    public boolean existeSucursalPorCorreo(String correo) {
        return repo.existsByCorreo(correo.trim());
    }

}
