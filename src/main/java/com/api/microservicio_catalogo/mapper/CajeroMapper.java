package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.CajeroRequest;
import com.api.microservicio_catalogo.dto.CajeroResponse;
import com.api.microservicio_catalogo.model.Cajero;

public class CajeroMapper {
public static CajeroResponse toResponse(Cajero cajero) {
        if (cajero == null)
            return null;
        return CajeroResponse.builder()
                .idCajero(cajero.getIdCajero())
                .idUsuario(cajero.getUsuario().getId())
                .nombre(cajero.getNombre())
                .turno(cajero.getTurno())
                .build();
    }

    public static Cajero toEntity(CajeroRequest dto) {
        if (dto == null)
            return null;
        return Cajero.builder()
                //.idUsuario(dto.getIdUsuario())
                .nombre(dto.getNombre())
                .turno(dto.getTurno())
                .build();
    }

    public static void copyToEntity(CajeroRequest dto, Cajero entity) {
        if (dto == null || entity == null)
            return;
        entity.setNombre(dto.getNombre());
        entity.setTurno(dto.getTurno());
        //entity.setIdUsuario(dto.getIdUsuario());
    }
}
