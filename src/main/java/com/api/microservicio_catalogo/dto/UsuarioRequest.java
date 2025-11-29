package com.api.microservicio_catalogo.dto;


import lombok.Data;

@Data
public class UsuarioRequest {
    
    private String email;
    private String password;
    private String role;

}
