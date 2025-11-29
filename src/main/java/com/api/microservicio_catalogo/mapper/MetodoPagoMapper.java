package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.MetodoPagoRequest;
import com.api.microservicio_catalogo.dto.MetodoPagoResponse;
import com.api.microservicio_catalogo.model.MetodosPago;

public final class MetodoPagoMapper {
    public static MetodoPagoResponse toResponse(MetodosPago metodoPago) {
        if (metodoPago == null)
            return null;
        return MetodoPagoResponse.builder()
                .idMetodoPago(metodoPago.getIdMetodoPago())
                .tipoPago(metodoPago.getTipoPago())
                .build();
    }

    public static MetodosPago toEntity(MetodoPagoRequest dto) {
        if (dto == null)
            return null;
        return MetodosPago.builder()
                .tipoPago(dto.getTipoPago())
                .build();
    }

    public static void copyToEntity(MetodoPagoRequest dto, MetodosPago entity) {
        if (dto == null || entity == null)
            return;
        entity.setTipoPago(dto.getTipoPago());
    }
}
