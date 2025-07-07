package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.audit.Auditable;
import com.tailoy.inv.audit.ModuloEnum;
import com.tailoy.inv.audit.TipoAccionEnum;
import com.tailoy.inv.command.ActualizarSubcategoriaCommand;
import com.tailoy.inv.command.RegistrarSubcategoriaCommand;
import com.tailoy.inv.dto.SubcategoriaDTO;
import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.repository.CategoriaRepository;
import com.tailoy.inv.repository.SubcategoriaRepository;
import com.tailoy.inv.service.SubcategoriaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SubcategoriaServiceImpl implements SubcategoriaService {
    @Autowired
    private SubcategoriaRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepo;

    @Autowired
    private RegistrarSubcategoriaCommand registrarSubcategoriaCommand;

    @Autowired
    private ActualizarSubcategoriaCommand actualizarSubcategoriaCommand;

    @Auditable(accion = "Registro de subcategorías", tipo = TipoAccionEnum.REGISTRO, modulo = ModuloEnum.SUBCATEGORIA)
    @Override
    @Transactional
    public SubcategoriaDTO registrarSubcategoria(SubcategoriaDTO subcategoriaDTO) {
        return registrarSubcategoriaCommand.ejecutar(subcategoriaDTO, null);
    }

    @Override
    public List<SubcategoriaDTO> listarSubcategorias() {
        return repo.findAll().stream().map(SubcategoriaDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean existeSubcategoriaPorNombre(String nombre) {
        return repo.existsByNombre(nombre.trim());
    }

    @Auditable(accion = "Modificación de subcategorías", tipo = TipoAccionEnum.MODIFICACION, modulo = ModuloEnum.SUBCATEGORIA)
    @Override
    @Transactional
    public SubcategoriaDTO actualizarSubcategoria(int id, SubcategoriaDTO subcategoriaDTO) {
        return actualizarSubcategoriaCommand.ejecutar(subcategoriaDTO, id);
    }

    @Override
    public List<SubcategoriaDTO> listarSubcategoriasPorCategoria(int idCategoria) {
        Categoria categoria = categoriaRepo.findById(idCategoria)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + idCategoria));

        List<SubcategoriaDTO> subcategorias = repo.findByCategoria(categoria);
        return subcategorias;
    }

    @Override
    public List<SubcategoriaDTO> buscarPorNombre(String nombre) {
        List<SubcategoriaDTO> subcategorias = repo.findByNombreContainingIgnoreCase(nombre.trim());
        return subcategorias;
    }

}
