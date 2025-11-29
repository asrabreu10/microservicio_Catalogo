package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.ProductoRequest;
import com.api.microservicio_catalogo.dto.ProductoResponse;
import com.api.microservicio_catalogo.model.Producto;

public final class ProductoMapper {
     
    
    public static ProductoResponse toResponse(Producto producto) {
        if (producto == null)
            return null;
        return ProductoResponse.builder()
                .idProducto(producto.getIdProducto())
                .nombreProducto(producto.getNombreProducto())
                .descripcion(producto.getDescripcion())
                .stock(producto.getStock())
                .idCategoria(producto.getCategoria().getIdCategoria())
                .idProveedor(producto.getProveedor().getIdProveedor())
                .build();
    }

    public static Producto toEntity(ProductoRequest dto) {
        if (dto == null)
            return null;
        return Producto.builder()
                //.idCategoria(dto.getIdCategoria())
                //.idProveedor(dto.getIdProveedor())
                .nombreProducto(dto.getNombreProducto())
                .descripcion(dto.getDescripcion())
                .stock(dto.getStock() != null ? dto.getStock() : 0) // Inicializar stock si es nulo
                .build();
    }

    public static void copyToEntity(ProductoRequest dto, Producto entity) {
        if (dto == null || entity == null)
            return;
        //entity.setIdCategoria(dto.getIdCategoria());
        //entity.setIdProveedor(dto.getIdProveedor());
        entity.setNombreProducto(dto.getNombreProducto());
        entity.setDescripcion(dto.getDescripcion());
        // El stock se puede actualizar por aquí, aunque es mejor hacerlo mediante la lógica de Inventario/Ventas
        if (dto.getStock() != null) {
            entity.setStock(dto.getStock());
        }
    }
}
