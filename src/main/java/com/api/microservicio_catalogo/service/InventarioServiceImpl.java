package com.api.microservicio_catalogo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio_catalogo.dto.InventarioRequest;
import com.api.microservicio_catalogo.dto.InventarioResponse;
import com.api.microservicio_catalogo.mapper.InventarioMapper;
import com.api.microservicio_catalogo.model.Inventario;
import com.api.microservicio_catalogo.model.Producto;
import com.api.microservicio_catalogo.repository.InventarioRepository;
import com.api.microservicio_catalogo.repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService{
       private final InventarioRepository repository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public InventarioResponse findByProductId(Integer idProducto) {
        Inventario inventario = repository.findInventoryByProductId(idProducto)
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado para el producto: " + idProducto));
        return InventarioMapper.toResponse(inventario);
    }

    @Override
    @Transactional
    public InventarioResponse createOrUpdate(InventarioRequest req) {
        Integer idProducto = req.getIdProducto();
        
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + idProducto));
        
        Optional<Inventario> existingInventory = repository.findInventoryByProductId(idProducto);
        Inventario inventario;

        if (existingInventory.isPresent()) {
            inventario = existingInventory.get();
            inventario.setCantidad(req.getCantidad()); 
        } else {
            inventario = InventarioMapper.toEntity(req);
        }

        producto.setStock(req.getCantidad()); 
        productoRepository.save(producto);

        Inventario saved = repository.save(inventario);
        return InventarioMapper.toResponse(saved);
    }

    @Override
    public List<InventarioResponse> getAll(int page, int pageSize) {
		PageRequest pageReq = PageRequest.of(page, pageSize);
		Page<Inventario> inventario = repository.findAll(pageReq);
		return inventario.getContent().stream()
                .map(InventarioMapper::toResponse)
                .toList();
	}
}
