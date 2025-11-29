package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.VentaRequest;
import com.api.microservicio_catalogo.dto.VentaResponse;
import com.api.microservicio_catalogo.model.Venta;

public final class VentaMapper {
    public static VentaResponse toResponse(Venta venta) {
        if (venta == null)
            return null;
        return VentaResponse.builder()
                .idVenta(venta.getIdVenta())
                .idCliente(venta.getCliente().getIdCliente())
                .idCajero(venta.getCajero().getIdCajero())
                .idMetodoPago(venta.getMetodoPago().getIdMetodoPago())
                .fechaVenta(venta.getFechaVenta())
                .total(venta.getTotal())
                .build();
    }

    public static Venta toEntity(VentaRequest dto) {
        if (dto == null)
            return null;
        return Venta.builder()
                //.idCliente(dto.getIdCliente())
                //.idCajero(dto.getIdCajero())
                //.idMetodoPago(dto.getIdMetodoPago())
                .total(dto.getTotal())
                // La fecha se establece automáticamente en el Service/Model
                .build();
    }

    // Normalmente no se usa 'copyToEntity' para Ventas, ya que las ventas no se actualizan después de crearse.
    // Pero se incluye por completitud, aunque con precaución.
    public static void copyToEntity(VentaRequest dto, Venta entity) {
        if (dto == null || entity == null)
            return;
        //entity.setIdCliente(dto.getIdCliente());
        //entity.setIdCajero(dto.getIdCajero());
        //entity.setIdMetodoPago(dto.getIdMetodoPago());
        //entity.setTotal(dto.getTotal());
        // No se copia la fecha
    }
}
