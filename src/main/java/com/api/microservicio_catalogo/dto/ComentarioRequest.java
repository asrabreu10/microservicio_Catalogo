package com.api.microservicio_catalogo.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class ComentarioRequest {
    @Size(max = 100)
    private String titulo;

    @Size(max = 100)
    private String comentario;

    @Size(max = 100)
    private String autor;
}
