package com.api.microservicio_catalogo.dto;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

// Este DTO representa la VENTA completa, incluyendo sus detalles
@Data
public class VentaRequest {
  @NotNull
    private Integer idCajero; // FK

    private Integer idCliente; // FK (Opcional)

    private Integer idMetodoPago; // FK (Opcional)

    // El total se puede calcular en el backend, pero se incluye para validación si se envía
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal total; 

    // Lista de productos en la venta (Tabla DETALLE_VENTAS)
    @Valid
    @NotEmpty
    private List<DetalleVentaRequest> detalles;
}
