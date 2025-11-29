package com.api.microservicio_catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio_catalogo.dto.ProductoRequest;
import com.api.microservicio_catalogo.dto.ProductoResponse;
import com.api.microservicio_catalogo.mapper.ProductoMapper;
import com.api.microservicio_catalogo.model.Producto;
import com.api.microservicio_catalogo.repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> findAll() {
        return repository.findAll().stream()
                .map(ProductoMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponse findById(Integer idProducto) {
        Producto producto = repository.findById(idProducto)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + idProducto));
        return ProductoMapper.toResponse(producto);
    }

    @Override
    @Transactional
    public ProductoResponse create(ProductoRequest req) {
        Producto saved = repository.save(ProductoMapper.toEntity(req));
        return ProductoMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ProductoResponse update(Integer idProducto, ProductoRequest req) {
        Producto existing = repository.findById(idProducto)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + idProducto));

        ProductoMapper.copyToEntity(req, existing);

        Producto saved = repository.save(existing);
        return ProductoMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer idProducto) {
        if (!repository.existsById(idProducto)) {
            throw new EntityNotFoundException("Producto no encontrado: " + idProducto);
        }
        // Los registros de inventario y detalle_ventas se eliminan (ON DELETE CASCADE)
        repository.deleteById(idProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> findByCategoria(Integer idCategoria) {
        return repository.findByCategoria_IdCategoria(idCategoria).stream()
                .map(ProductoMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> findProductsWithLowStock(Integer cantidadMinima) {
        return repository.findProductsWithLowStock(cantidadMinima).stream()
                .map(ProductoMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductoResponse> findAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Producto> producto = repository.findAll(pageReq);
        return producto.getContent().stream()
                .map(ProductoMapper::toResponse)
                .toList();
    }

}
