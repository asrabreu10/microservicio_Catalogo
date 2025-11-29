package com.api.microservicio_catalogo.mapper;

import java.util.Iterator;
import java.util.Set;

import com.api.microservicio_catalogo.dto.UsuarioRequest;
import com.api.microservicio_catalogo.dto.UsuarioResponse;
import com.api.microservicio_catalogo.model.Role;
import com.api.microservicio_catalogo.model.Usuario;

public final class UsuarioMapper {

    public static UsuarioResponse toResponse(Usuario user) {
        if (user == null) return null;

        Set<Role> roles = user.getAuthorities();
        Role firstElement = new Role();
        if (!roles.isEmpty()) {
            Iterator<Role> iterator = roles.iterator();
            firstElement = iterator.next();
        }

        return UsuarioResponse.builder()
                .email(user.getEmail())
                .role(firstElement.getAuthority())
                .build();
    }

    public static Usuario toEntity(UsuarioRequest dto) {
        if (dto == null) return null;

        Usuario user = Usuario.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return user;
    }

    public static void copyToEntity(UsuarioRequest dto, Usuario entity) {
        if (dto == null || entity == null) return;
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}
