package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.ClienteRequest;
import com.api.microservicio_catalogo.dto.ClienteResponse;
import com.api.microservicio_catalogo.model.Cliente;

public final class ClienteMapper {
    public static ClienteResponse toResponse(Cliente cliente) {
        if (cliente == null)
            return null;
        return ClienteResponse.builder()
                .idCliente(cliente.getIdCliente())
                .idDireccion(cliente.getDireccion().getIdDireccion())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
    }

    public static Cliente toEntity(ClienteRequest dto) {
        if (dto == null)
            return null;
        return Cliente.builder()
                //.idDireccion(dto.getIdDireccion())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .build();
    }

    public static void copyToEntity(ClienteRequest dto, Cliente entity) {
        if (dto == null || entity == null)
            return;
        //entity.setIdDireccion(dto.getIdDireccion());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
    }
}
