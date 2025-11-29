package com.api.microservicio_catalogo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DetalleVentaRequest {

        // Nota: El idVenta NO se incluye aquí, se toma de la VentaRequest padre.

    @NotNull
    private Integer idProducto; // FK

    @NotNull
    @Min(value = 1)
    private Integer cantidad;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal precioUnitario; 
    
    // El campo subtotal es GENERATED ALWAYS, por lo que no se requiere en el Request,
    // pero se podría usar para validar la consistencia si el cliente lo envía.
    
}
