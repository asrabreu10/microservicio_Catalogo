package com.api.microservicio_catalogo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductoRequest {
    @NotBlank
    @Size(max = 100)
    private String nombreProducto;

    @NotBlank
    @Size(max = 100)
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal precio;

    @Min(value = 0)
    private Integer stock; 
   
    private Integer idCategoria; 
    
    private Integer idProveedor; 
}
