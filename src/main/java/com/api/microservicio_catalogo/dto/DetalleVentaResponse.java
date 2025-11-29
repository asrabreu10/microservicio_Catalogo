package com.api.microservicio_catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;

@Value
@Builder

public class DetalleVentaResponse {
    @JsonProperty("Id detalle:")
    Integer idDetalle;
    
    @JsonProperty("Id venta:")
    Integer idVenta;

    @JsonProperty("Id producto:")
    Integer idProducto;

    Integer cantidad;

    @JsonProperty("Precio unitario:")
    BigDecimal precioUnitario;

    BigDecimal subtotal; // Campo generado
}
