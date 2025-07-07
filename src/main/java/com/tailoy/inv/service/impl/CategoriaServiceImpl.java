package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.command.ActualizarCategoriaCommand;
import com.tailoy.inv.command.RegistrarCategoriaCommand;
import com.tailoy.inv.dto.CategoriaDTO;
import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.repository.CategoriaRepository;
import com.tailoy.inv.service.CategoriaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private RegistrarCategoriaCommand registrarCategoriaCommand;

    @Autowired
    private ActualizarCategoriaCommand actualizarCategoriaCommand;

    @Auditable(accion = "Registro de categorías", tipo = TipoAccionEnum.REGISTRO, modulo = ModuloEnum.CATEGORIA)
    @Override
    public CategoriaDTO registrarCategoria(CategoriaDTO categoriaDTO) {
        return registrarCategoriaCommand.ejecutar(categoriaDTO, null);
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorId(int id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La categoría no existe"));
        return new CategoriaDTO(categoria);
    }

    @Auditable(accion = "Modificación de categorías", tipo = TipoAccionEnum.MODIFICACION, modulo = ModuloEnum.CATEGORIA)
    @Override
    public CategoriaDTO actualizarCategoria(int id, CategoriaDTO categoriaDTO) {
        return actualizarCategoriaCommand.ejecutar(categoriaDTO, id);
    }

    @Override
    public boolean existeCategoriaPorNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre.trim());
    }

    @Override
    public List<CategoriaDTO> buscarPorNombre(String nombre) {
        List<CategoriaDTO> categorias = categoriaRepository.findByNombreContainingIgnoreCase(nombre);
        return categorias;
    }
}
