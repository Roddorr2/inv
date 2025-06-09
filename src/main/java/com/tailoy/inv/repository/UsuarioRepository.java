package com.tailoy.inv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tailoy.inv.dto.UsuarioDTO;
import com.tailoy.inv.model.Cargo;
import com.tailoy.inv.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByCorreo(String correo);
    @Query("SELECT u FROM Usuario u WHERE " +
			"LOWER(u.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
			"OR LOWER(u.correo) LIKE LOWER(CONCAT('%', :term, '%')) ")
    List<UsuarioDTO> findByNombreOrCorreoContainingIgnoreCase(@Param("term") String term);
    boolean existsByNombre(String nombre);
    boolean existsByCorreo(String correo);
    List<UsuarioDTO> findByCargo(Cargo cargo);
}
