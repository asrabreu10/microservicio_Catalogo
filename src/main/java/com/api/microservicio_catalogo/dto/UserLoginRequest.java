package com.api.microservicio_catalogo.dto;


import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
