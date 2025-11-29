package com.api.microservicio_catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio_catalogo.dto.CategoriaProductoRequest;
import com.api.microservicio_catalogo.dto.CategoriaProductoResponse;
import com.api.microservicio_catalogo.mapper.CategoriaProductoMapper;
import com.api.microservicio_catalogo.model.CategoriaProducto;
import com.api.microservicio_catalogo.repository.CategoriaProductoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaProductosServiceImpl implements CategoriaProductosService {
    private final CategoriaProductoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaProductoResponse> findAll() {
        return repository.findAll().stream()
                .map(CategoriaProductoMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaProductoResponse findById(Integer idCategoria) {
        CategoriaProducto categoria = repository.findById(idCategoria)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + idCategoria));
        return CategoriaProductoMapper.toResponse(categoria);
    }

    @Override
    @Transactional
    public CategoriaProductoResponse create(CategoriaProductoRequest req) {
        if (repository.findCategoryByName(req.getNombreCategoria()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + req.getNombreCategoria());
        }
        CategoriaProducto saved = repository.save(CategoriaProductoMapper.toEntity(req));
        return CategoriaProductoMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public CategoriaProductoResponse update(Integer idCategoria, CategoriaProductoRequest req) {
        CategoriaProducto existing = repository.findById(idCategoria)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + idCategoria));

        CategoriaProductoMapper.copyToEntity(req, existing);

        CategoriaProducto saved = repository.save(existing);
        return CategoriaProductoMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer idCategoria) {
        if (!repository.existsById(idCategoria)) {
            throw new EntityNotFoundException("Categoría no encontrada: " + idCategoria);
        }
        // La eliminación se maneja por la base de datos (ON DELETE SET NULL en
        // Producto)
        repository.deleteById(idCategoria);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaProductoResponse findCategoryByName(String nombreCategoria) {
        CategoriaProducto categoria = repository.findCategoryByName(nombreCategoria)
                .orElseThrow(
                        () -> new EntityNotFoundException("Categoría no encontrada con nombre: " + nombreCategoria));
        return CategoriaProductoMapper.toResponse(categoria);
    }

    //// Paginacion
    @Override
    public List<CategoriaProductoResponse> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<CategoriaProducto> ctp = repository.findAll(pageReq);
        return ctp.getContent().stream()
                .map(CategoriaProductoMapper::toResponse)
                .toList();
    }
}
