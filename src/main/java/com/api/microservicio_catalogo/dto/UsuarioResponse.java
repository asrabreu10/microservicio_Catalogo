package com.api.microservicio_catalogo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UsuarioResponse {
    private String email;
    private String role;
}
