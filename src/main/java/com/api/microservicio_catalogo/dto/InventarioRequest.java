package com.api.microservicio_catalogo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class InventarioRequest {
    @NotNull
    private Integer idProducto; // FK

    @NotNull
    @Min(value = 1)
    private Integer cantidad;
}
