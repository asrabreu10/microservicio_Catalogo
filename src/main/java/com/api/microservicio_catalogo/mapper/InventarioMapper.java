package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.InventarioRequest;
import com.api.microservicio_catalogo.dto.InventarioResponse;
import com.api.microservicio_catalogo.model.Inventario;

public final class InventarioMapper {
    
public static InventarioResponse toResponse(Inventario inventario) {
        if (inventario == null)
            return null;
        return InventarioResponse.builder()
                .idInventario(inventario.getIdInventario())
                .idProducto(inventario.getProducto().getIdProducto())
                .cantidad(inventario.getCantidad())
                .fechaActualizacion(inventario.getFechaActualizacion())
                .build();
    }

    public static Inventario toEntity(InventarioRequest dto) {
        if (dto == null)
            return null;
        return Inventario.builder()
                //.idProducto(dto.getIdProducto())
                .cantidad(dto.getCantidad())
                // La fecha de actualización se establece en el service/model
                .build();
    }

    public static void copyToEntity(InventarioRequest dto, Inventario entity) {
        if (dto == null || entity == null)
            return;
        entity.setCantidad(dto.getCantidad());
        // La actualización de fecha se maneja en el Model o Service
    }
}
