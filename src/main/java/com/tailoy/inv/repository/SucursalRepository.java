package com.tailoy.inv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.dto.SucursalDTO;
import com.tailoy.inv.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
	List<SucursalDTO> findByDireccionContainingIgnoreCaseOrCorreoContainingIgnoreCase(String direccion, String correo);
	boolean existsByCorreo(String correo);
	List<SucursalDTO> findByCorreo(String correo);
}
