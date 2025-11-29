package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.DetalleVentaRequest;
import com.api.microservicio_catalogo.dto.DetalleVentaResponse;
import com.api.microservicio_catalogo.model.DetalleVenta;

public final class DetalleVentaMapper {
    public static DetalleVentaResponse toResponse(DetalleVenta detalle) {
        if (detalle == null)
            return null;
        return DetalleVentaResponse.builder()
                .idDetalle(detalle.getIdDetalle())
                .idVenta(detalle.getVenta().getIdVenta())
                .idProducto(detalle.getProducto().getIdProducto())
                .cantidad(detalle.getCantidad())
                .precioUnitario(detalle.getPrecioUnitario())
                .subtotal(detalle.getSubtotal())
                .build();
    }

    public static DetalleVenta toEntity(DetalleVentaRequest dto) {
        if (dto == null)
            return null;
        
        // Calcular subtotal en el mapper o en el service
        // Lo calcularemos aquí, aunque el service lo podría recalcular para mayor seguridad.
        java.math.BigDecimal subtotal = dto.getPrecioUnitario()
            .multiply(new java.math.BigDecimal(dto.getCantidad()));

        return DetalleVenta.builder()
                // idVenta se asigna en el Service después de crear la Venta principal
                //.idProducto(dto.getIdProducto())
                .cantidad(dto.getCantidad())
                .precioUnitario(dto.getPrecioUnitario())
                .subtotal(subtotal)
                .build();
    }
}
