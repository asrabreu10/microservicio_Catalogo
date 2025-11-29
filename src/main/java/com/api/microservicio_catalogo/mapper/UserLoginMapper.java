package com.api.microservicio_catalogo.mapper;
import java.util.Iterator;
import java.util.Set;

import com.api.microservicio_catalogo.dto.UserLoginRequest;
import com.api.microservicio_catalogo.dto.UserLoginResponse;
import com.api.microservicio_catalogo.model.Role;
import com.api.microservicio_catalogo.model.Usuario;

public final class UserLoginMapper {

    public static UserLoginResponse toResponse(Usuario user) {
        if (user == null) return null;

        Set<Role> roles = user.getAuthorities();
        Role firstElement = new Role();
        if (!roles.isEmpty()) {
            Iterator<Role> iterator = roles.iterator();
            firstElement = iterator.next();
        }

        return UserLoginResponse.builder()
                .email(user.getEmail())
                .role(firstElement.getAuthority())
                .build();
    }

    public static Usuario toEntity(UserLoginRequest dto) {
        if (dto == null) return null;

        Usuario user = Usuario.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return user;
    }

    public static void copyToEntity(UserLoginRequest dto, Usuario entity) {
        if (dto == null || entity == null) return;
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}
