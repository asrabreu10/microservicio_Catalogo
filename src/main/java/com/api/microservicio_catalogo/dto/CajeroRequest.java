package com.api.microservicio_catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CajeroRequest {
    @NotNull
    private Integer idUsuario; // FK

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @NotBlank
    // Se asume que el valor de turno debe ser uno de los permitidos ('matutino','vespertino','nocturno')
    private String turno;
}
