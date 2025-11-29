package com.api.microservicio_catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MetodoPagoResponse {
    @JsonProperty("Id metodo pago:")
    Integer idMetodoPago;
    
    @JsonProperty("Tipo pago:")
    String tipoPago;

    @JsonProperty("Descripcion")
    String descripcion;
}
