package com.api.microservicio_catalogo.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class DireccionRequest {
    @Size(max = 100)
    private String calle;

    @Size(max = 10)
    private String numero;

    @Size(max = 100)
    private String colonia;

    @Size(max = 100)
    private String ciudad;

    @Size(max = 100)
    private String estado;

    @Size(max = 10)
    private String codigoPostal;
}
