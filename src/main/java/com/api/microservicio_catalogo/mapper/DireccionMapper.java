package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.DireccionRequest;
import com.api.microservicio_catalogo.dto.DireccionResponse;
import com.api.microservicio_catalogo.model.Direccion;

public final class DireccionMapper {
     public static DireccionResponse toResponse(Direccion direccion) {
        if (direccion == null)
            return null;
        return DireccionResponse.builder()
                .idDireccion(direccion.getIdDireccion())
                .calle(direccion.getCalle())
                .numero(direccion.getNumero())
                .colonia(direccion.getColonia())
                .codigoPostal(direccion.getCodigoPostal())
                .ciudad(direccion.getCiudad())
                .estado(direccion.getEstado())
                .build();
    }

    public static Direccion toEntity(DireccionRequest dto) {
        if (dto == null)
            return null;
        return Direccion.builder()
                .calle(dto.getCalle())
                .numero(dto.getNumero())
                .colonia(dto.getColonia())
                .codigoPostal(dto.getCodigoPostal())
                .ciudad(dto.getCiudad())
                .estado(dto.getEstado())
                .build();
    }

    public static void copyToEntity(DireccionRequest dto, Direccion entity) {
        if (dto == null || entity == null)
            return;
        entity.setCalle(dto.getCalle());
        entity.setNumero(dto.getNumero());
        entity.setColonia(dto.getColonia());
        entity.setCodigoPostal(dto.getCodigoPostal());
        entity.setCiudad(dto.getCiudad());
        entity.setEstado(dto.getEstado());
    }
}
