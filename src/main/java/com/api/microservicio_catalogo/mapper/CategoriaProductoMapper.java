package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.CategoriaProductoRequest;
import com.api.microservicio_catalogo.dto.CategoriaProductoResponse;
import com.api.microservicio_catalogo.model.CategoriaProducto;

public final class CategoriaProductoMapper {
    public static CategoriaProductoResponse toResponse(CategoriaProducto categoria) {
        if (categoria == null)
            return null;
        return CategoriaProductoResponse.builder()
                .idCategoria(categoria.getIdCategoria())
                .nombreCategoria(categoria.getNombreCategoria())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    public static CategoriaProducto toEntity(CategoriaProductoRequest dto) {
        if (dto == null)
            return null;
        return CategoriaProducto.builder()
                .nombreCategoria(dto.getNombreCategoria())
                .descripcion(dto.getDescripcion())
                .build();
    }

    public static void copyToEntity(CategoriaProductoRequest dto, CategoriaProducto entity) {
        if (dto == null || entity == null)
            return;
        entity.setNombreCategoria(dto.getNombreCategoria());
        entity.setDescripcion(dto.getDescripcion());
    }
}
