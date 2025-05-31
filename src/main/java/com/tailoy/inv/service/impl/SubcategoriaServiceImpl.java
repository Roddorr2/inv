package com.tailoy.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tailoy.inv.dto.CategoriaDTO;
import com.tailoy.inv.dto.SubcategoriaDTO;
import com.tailoy.inv.model.Categoria;
import com.tailoy.inv.model.Subcategoria;
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
	
	@Override
    @Transactional
    public SubcategoriaDTO registrarSubcategoria(SubcategoriaDTO subcategoriaDTO) {
        if (existeSubcategoriaPorNombre(subcategoriaDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe una subcategoría con ese nombre.");
        }

        CategoriaDTO categoriaDTO = subcategoriaDTO.getCategoria();
        Categoria categoria = categoriaRepo.findById(categoriaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + categoriaDTO.getId()));

        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setNombre(subcategoriaDTO.getNombre());
        subcategoria.setCategoria(categoria);

        Subcategoria guardada = repo.save(subcategoria);
        return new SubcategoriaDTO(guardada);
    }

    @Override
    public List<SubcategoriaDTO> listarSubcategorias() {
        return repo.findAll().stream().map(SubcategoriaDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean existeSubcategoriaPorNombre(String nombre) {
        return repo.existsByNombre(nombre.trim());
    }
	

}
