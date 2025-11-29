package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.ProveedorRequest;
import com.api.microservicio_catalogo.dto.ProveedorResponse;
import com.api.microservicio_catalogo.model.Proveedor;

public final class ProveedorMapper {
     public static ProveedorResponse toResponse(Proveedor proveedor) {
        if (proveedor == null)
            return null;
        return ProveedorResponse.builder()
                .idProveedor(proveedor.getIdProveedor())
                .idDireccion(proveedor.getDireccion().getIdDireccion())
                .nombreEmpresa(proveedor.getNombreEmpresa())
                .contacto(proveedor.getContacto())
                .telefono(proveedor.getTelefono())
                .email(proveedor.getEmail())
                .build();
    }

    public static Proveedor toEntity(ProveedorRequest dto) {
        if (dto == null)
            return null;
        return Proveedor.builder()
                //.idDireccion(dto.getIdDireccion())
                .nombreEmpresa(dto.getNombreEmpresa())
                .contacto(dto.getContacto())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .build();
    }

    public static void copyToEntity(ProveedorRequest dto, Proveedor entity) {
        if (dto == null || entity == null)
            return;
        //entity.setIdDireccion(dto.getIdDireccion());
        entity.setNombreEmpresa(dto.getNombreEmpresa());
        entity.setContacto(dto.getContacto());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
    }
}
