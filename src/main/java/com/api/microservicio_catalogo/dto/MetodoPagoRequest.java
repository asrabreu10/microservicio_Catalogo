package com.api.microservicio_catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MetodoPagoRequest {
    @NotBlank
    // Se asume que el valor de tipoPago debe ser uno de los permitidos ('efectivo','tarjeta','transferencia','otros')
    private String tipoPago;
    
    @NotBlank
    @Size(max = 100)
    private String descripcion;
}
