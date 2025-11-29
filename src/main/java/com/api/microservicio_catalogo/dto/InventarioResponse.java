package com.api.microservicio_catalogo.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InventarioResponse {
    @JsonProperty("Id inventario:")
    Integer idInventario;
    
    @JsonProperty("Id producto:")
    Integer idProducto;
    
    Integer cantidad;
    
    @JsonProperty("Fecha actualizacion:")
    Instant fechaActualizacion;
}
