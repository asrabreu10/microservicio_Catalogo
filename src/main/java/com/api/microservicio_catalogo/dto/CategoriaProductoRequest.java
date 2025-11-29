package com.api.microservicio_catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaProductoRequest {
    @NotBlank
    @Size(max = 100)
    private String nombreCategoria;
   
    @NotBlank
    @Size(max = 100)
    private String descripcion;
}
